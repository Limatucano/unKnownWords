package com.correa.unknownword.ui.game

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.correa.unknownword.domain.entities.GroupEntity
import com.correa.unknownword.domain.entities.WordEntity
import com.correa.unknownword.domain.entities.WordStatus
import com.correa.unknownword.domain.usecases.GetUnknownWordsUseCase
import com.correa.unknownword.domain.usecases.LoadGroupsUseCase
import com.correa.unknownword.utils.Constants
import com.correa.unknownword.utils.Constants.INITIAL_WORDS
import com.correa.unknownword.utils.Constants.INTERVAL_TIME
import com.correa.unknownword.utils.Constants.MAX_ROUND
import com.correa.unknownword.utils.Constants.NEED_TO_RIGHT
import com.correa.unknownword.utils.Constants.formatTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val getUnknownWordsUseCase: GetUnknownWordsUseCase,
    private val loadGroupsUseCase: LoadGroupsUseCase,
) : ViewModel() {

    private var countDownTimer: CountDownTimer? = null
    private var countDownLoadingTimer: CountDownTimer? = null

    private val _state = MutableLiveData(GameState())
    val state: LiveData<GameState> = _state

    init {
        startInitialLoading()
    }

    private fun startInitialLoading() {
        _state.value = state.value?.copy(
            isLoading = true
        )
        countDownLoadingTimer?.cancel()

        countDownLoadingTimer = object : CountDownTimer(
            Constants.INITIAL_TIME,
            INTERVAL_TIME
        ) {
            override fun onTick(millisRemaining: Long) {
                val seconds: String= (TimeUnit.MILLISECONDS.toSeconds(millisRemaining) % 60).toString()
                val minutes = (millisRemaining / INTERVAL_TIME)
                _state.value = state.value?.copy(
                    time = seconds
                )
                if(minutes == 0L){
                    _state.value = state.value?.copy(
                        isLoading = false
                    )
                    startTimer()
                }
            }

            override fun onFinish() {
                countDownLoadingTimer?.cancel()
            }

        }.start()
    }

    fun checkEndGame() {
        val howManyRights = state.value?.words?.filter {
            it.wordStatus == WordStatus.RIGHT
        }?.size ?: 0

        val howManyNotSelected = state.value?.words?.filter {
            it.wordStatus == WordStatus.DEFAULT
        }?.size ?: 0

        if((howManyRights + howManyNotSelected) < NEED_TO_RIGHT) {
            finishGame(
                currentGroup = state.value?.currentGroup!!,
                isWin = false
            )
        }

        if(howManyRights == NEED_TO_RIGHT && state.value?.currentGroup != null) {
            finishGame(
                currentGroup = state.value?.currentGroup!!,
                isWin = true
            )
        }
    }

    fun restartGame() {
        val currentGroup = state.value?.currentGroup
        _state.value = state.value?.copy(
            currentGroup = getNextGroup(currentGroup),
            isFinishGame = false,
        )
        getUnknownWords(round = getNextGroup(currentGroup)?.round ?: 0)
        startInitialLoading()
    }

    private fun getNextGroup(currentGroup: GroupEntity?): GroupEntity? {
        val currentGroupIndex = state.value?.groups?.indexOf(currentGroup) ?: 0

        return state.value?.groups?.getOrNull(currentGroupIndex + 1) ?: state.value?.groups?.get(0)
    }

    private fun finishGame(
        currentGroup: GroupEntity,
        isWin: Boolean
    ) {
        cancelTimer()
        val currentGroupUpdated = currentGroup.copy(
            round = if(currentGroup.round == (MAX_ROUND - 1) || !isWin) currentGroup.round else currentGroup.round + 1,
            isTurn = false
        )

        val nextGroup = getNextGroup(currentGroup)

        val groupsUpdated = state.value?.groups?.map {
            val group = updateGroup(
                group = it,
                currentGroup = currentGroupUpdated,
                nextGroup = nextGroup
            )
            GroupEntity(
                round = group.round,
                name = group.name,
                isTurn = group.isTurn
            )
        }

        groupsUpdated?.let {
            _state.value = state.value?.copy(
                currentGroup = currentGroupUpdated,
                groups = it,
                isFinishGame = currentGroup.round != (MAX_ROUND - 1),
                isWin = isWin,
                isEndGame = currentGroup.round == (MAX_ROUND - 1)
            )
        }
    }

    private fun updateGroup(
        group: GroupEntity,
        currentGroup: GroupEntity,
        nextGroup: GroupEntity?,
    ): GroupEntity {
        return when(group.name) {
            currentGroup.name -> {
                GroupEntity(
                    round = currentGroup.round,
                    name = currentGroup.name,
                    isTurn = currentGroup.isTurn
                )
            }
            nextGroup?.name -> {
                GroupEntity(
                    round = nextGroup.round,
                    name = nextGroup.name,
                    isTurn = true
                )
            }
            else -> {
                group
            }
        }
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(
            Constants.MAX_TIME_PER_ROUND,
            INTERVAL_TIME
        ) {
            override fun onTick(millisRemaining: Long) {
                val progressValue = millisRemaining.toFloat() / Constants.MAX_TIME_PER_ROUND
                val minutes = (millisRemaining / INTERVAL_TIME)
                _state.value = state.value?.copy(
                    time = millisRemaining.formatTime(),
                    progressTimer = progressValue,
                )
                if(minutes == 0L && state.value?.currentGroup != null) {
                    finishGame(
                        currentGroup = state.value?.currentGroup!!,
                        isWin = false
                    )
                }
            }

            override fun onFinish() {
                cancelTimer()
            }

        }.start()
    }
    private fun cancelTimer() {
        _state.value = state.value?.copy(
            time = 0L.formatTime(),
            progressTimer = 0.00F,
        )
        countDownTimer?.cancel()
    }

    fun updateWordStatus(
        wordEntity: WordEntity,
        wordStatus: WordStatus
    ) {
        viewModelScope.launch {
            val wordsUpdated = state.value?.words?.map {
                WordEntity(
                    word = it.word,
                    wordStatus = if(it == wordEntity) wordStatus else it.wordStatus
                )
            }
            wordsUpdated?.let { updated ->
                _state.value = state.value?.copy(
                    words = updated
                )
            }
        }
    }

    fun handleInit(
        quantity: Int
    ) {
        if(state.value?.groups.isNullOrEmpty()){
            loadGroups(quantity)
        }
    }

    private fun loadGroups(quantity: Int) {
        viewModelScope.launch {
            val groups = loadGroupsUseCase.invoke(quantity)
            val currentGroup = groups.firstOrNull { it.isTurn }
            _state.value = state.value?.copy(
                groups = groups,
                currentGroup = currentGroup
            )
            getUnknownWords(currentGroup?.round ?: 0)
        }
    }

    private fun getUnknownWords(round: Int){
        viewModelScope.launch {
            _state.value = state.value?.copy(
                words = getUnknownWordsUseCase.invoke(INITIAL_WORDS - round)
            )
        }
    }
}