package com.correa.unknownword.ui.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.correa.unknownword.ui.theme.TypographyRoboto
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.correa.unknownword.domain.entities.WordStatus
import com.correa.unknownword.ui.atomic.atoms.SimpleButtonAtom
import com.correa.unknownword.ui.theme.ContinueButton
import com.correa.unknownword.ui.theme.FinishButton
import com.correa.unknownword.ui.theme.background
import com.correa.unknownword.utils.Constants.MAX_ROUND

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun GameScreen(
    viewModel: GameViewModel,
    quantity: Int,
    onFinishClicked: () -> Unit
) {
    val state by viewModel.state.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.loadGroups(quantity = quantity)
    }

    Scaffold { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 32.dp,
                        bottom = 12.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Card(
                    elevation = CardDefaults.cardElevation(0.dp),
                    colors = CardDefaults.cardColors(containerColor = background),
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Text(
                        text = "${state?.currentGroup?.round.toString().padStart(2,'0')}/$MAX_ROUND",
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxHeight()
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                }
                Text(
                    text = state?.currentGroup?.name ?: "Error",
                    style = TypographyRoboto.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxHeight()
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
                SimpleButtonAtom(
                    onClick = {  },
                    text = FinishButton,
                    textStyle = TypographyRoboto.labelLarge,
                    isEnabled = true,
                    modifier = Modifier
                        .fillMaxHeight()
                )
            }

            LinearProgressIndicator(
                progress = state?.progressTimer ?: 0.0F,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            )

            LazyColumn(
                content = {
                    items(state?.words ?: listOf()) { word ->
                        val currentItem by rememberUpdatedState(word)
                        val dismissState = rememberDismissState(
                            confirmStateChange = {
                                when (it) {
                                    DismissValue.DismissedToEnd -> {
                                        viewModel.updateWordStatus(
                                            wordEntity = currentItem,
                                            wordStatus = WordStatus.RIGHT,
                                        )
                                        false
                                    }
                                    DismissValue.DismissedToStart -> {
                                        viewModel.updateWordStatus(
                                            wordEntity = currentItem,
                                            wordStatus = WordStatus.WRONG,
                                        )
                                        false
                                    }
                                    else -> false
                                }
                            }
                        )
                        SwipeToDismiss(
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