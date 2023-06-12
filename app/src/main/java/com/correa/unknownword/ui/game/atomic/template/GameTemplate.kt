package com.correa.unknownword.ui.game.atomic.template

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.correa.unknownword.domain.entities.WordEntity
import com.correa.unknownword.ui.game.FinishDialog
import com.correa.unknownword.ui.game.GameState
import com.correa.unknownword.ui.game.ResultDialog
import com.correa.unknownword.ui.game.atomic.molecules.GameHeader
import com.correa.unknownword.ui.game.atomic.molecules.GameTimer
import com.correa.unknownword.ui.theme.TypographyRoboto
import com.correa.unknownword.utils.Constants
import com.correa.unknownword.utils.formatToInitialZero

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun GameTemplate(
    state: GameState?,
    stateDismissToEndChanged: (WordEntity) -> Unit,
    stateDismissToStartChanged: (WordEntity) -> Unit,
    onFinishClicked: () -> Unit,
    onResultButtonClicked: () -> Unit,
) {

    if(state?.isEndGame == true) {
        FinishDialog(
            currentGroupName = state.currentGroup?.name ?: "",
        ) {
            onFinishClicked()
        }
    }

    if(state?.isFinishGame == true) {
        ResultDialog(
            currentGroupName = state.currentGroup?.name ?: "",
            isWin = state.isWin,
            nextRound = state.currentGroup?.round,
        ) {
            onResultButtonClicked()
        }
    }

    Scaffold { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            GameHeader(
                roundText = "${(state?.currentGroup?.round?.plus(1))?.formatToInitialZero()}/${Constants.MAX_ROUND.formatToInitialZero()}",
                groupName = state?.currentGroup?.name ?: "Error",
                onFinishClicked = onFinishClicked,
            )

            GameTimer(
                progress = state?.progressTimer ?: 0.0F,
                timeText = state?.time ?: "00:00"
            )

            LazyColumn(
                content = {
                    items(state?.words ?: listOf()) { word ->
                        val currentItem by rememberUpdatedState(word)
                        val dismissState = rememberDismissState(
                            confirmStateChange = {
                                when (it) {
                                    DismissValue.DismissedToEnd -> {
                                        stateDismissToEndChanged.invoke(currentItem)
                                        false
                                    }
                                    DismissValue.DismissedToStart -> {
                                        stateDismissToStartChanged.invoke(currentItem)
                                        false
                                    }
                                    else -> false
                                }
                            }
                        )
                        SwipeToDismiss(
                            modifier = Modifier
                                .pointerInput(Unit) {
                                   detectTapGestures(
                                       onPress = {
                                           if(state?.isFinishGame == true) {
                                               return@detectTapGestures
                                           }
                                       }
                                   )
                            },
                            state = dismissState,
                            background = {},
                            dismissContent = {
                                Card(
                                    elevation = CardDefaults.cardElevation(4.dp),
                                    colors = CardDefaults.cardColors(containerColor = word.wordStatus.color),
                                    modifier = Modifier
                                        .padding(
                                            horizontal = 20.dp,
                                            vertical = 12.dp,
                                        )
                                        .height(60.dp)
                                        .fillMaxWidth()
                                ) {
                                    Row(
                                        modifier = Modifier.padding(12.dp)
                                    ) {
                                        Image(
                                            painterResource(word.wordStatus.iconRes),
                                            contentDescription = "",
                                        )
                                        Spacer(modifier = Modifier.width(16.dp))
                                        Text(
                                            text = word.word,
                                            style = TypographyRoboto.labelLarge,
                                            color = word.wordStatus.textColor,
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .wrapContentHeight(align = Alignment.CenterVertically)
                                        )
                                    }
                                }
                            }
                        )
                    }
                },
                contentPadding = PaddingValues(vertical = 8.dp),
            )
        }
    }
}