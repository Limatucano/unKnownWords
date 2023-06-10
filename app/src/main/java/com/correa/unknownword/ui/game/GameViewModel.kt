package com.correa.unknownword.ui.game

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.correa.unknownword.domain.entities.WordEntity
import com.correa.unknownword.domain.entities.WordStatus
import com.correa.unknownword.domain.usecases.GetUnknownWordsUseCase
import com.correa.unknownword.domain.usecases.LoadGroupsUseCase
import com.correa.unknownword.utils.Constants
import com.correa.unknownword.utils.Constants.MAX_ROUND
import com.correa.unknownword.utils.Constants.formatTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val getUnknownWordsUseCase: GetUnknownWordsUseCase,
    private val loadGroupsUseCase: LoadGroupsUseCase,
) : ViewModel() {

    private var countDownTimer: CountDownTimer? = null

    private val _state = MutableLiveData(GameState())
    val state: LiveData<GameState> = _state

    init {
        countDownTimer = object : CountDownTimer(Constants.MAX_TIME_PER_ROUND, 1000) {
            override fun onTick(millisRemaining: Long) {
                val progressValue = millisRemaining.toFloat() / Constants.MAX_TIME_PER_ROUND
                _state.value = state.value?.copy(
                    time = millisRemaining.formatTime(),
                    progressTimer = progressValue,
                )
            }

            override fun onFinish() {
                _state.value = state.value?.copy(
                    time = Constants.MAX_TIME_PER_ROUND.formatTime(),
                    progressTimer = 0.00F,
                )
                countDownTimer?.cancel()
            }

        }.start()
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

    fun loadGroups(quantity: Int) {
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
                words = getUnknownWordsUseCase.invoke(MAX_ROUND - round)
            )
        }
    }
}