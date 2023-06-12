package com.correa.unknownword.ui.game

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.correa.unknownword.domain.entities.WordStatus
import com.correa.unknownword.ui.game.atomic.organims.GameLoadingOrganism
import com.correa.unknownword.ui.game.atomic.template.GameTemplate

@Composable
fun GameScreen(
    viewModel: GameViewModel,
    quantity: Int,
    onFinishClicked: (Boolean) -> Unit
) {
    val state by viewModel.state.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.handleInit(
            quantity = quantity
        )
    }

    state?.let {
        when(it.isLoading) {
            true -> {
                GameLoadingOrganism(
                    time = state?.time,
                    groupName = state?.currentGroup?.name
                )
            }
            else -> {
                GameTemplate(
                    state = it,
                    stateDismissToEndChanged = { wordEntity ->
                        viewModel.updateWordStatus(
                            wordEntity = wordEntity,
                            wordStatus = WordStatus.RIGHT,
                        )
                        viewModel.checkEndGame()
                    },
                    stateDismissToStartChanged = { wordEntity ->
                        viewModel.updateWordStatus(
                            wordEntity = wordEntity,
                            wordStatus = WordStatus.WRONG,
                        )
                        viewModel.checkEndGame()
                    },
                    onFinishClicked = { onFinishClicked.invoke(false) },
                    onResultButtonClicked = { viewModel.restartGame() }
                )
            }
        }
    }

}