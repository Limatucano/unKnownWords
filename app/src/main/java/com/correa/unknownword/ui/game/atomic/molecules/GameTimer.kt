package com.correa.unknownword.ui.game.atomic.molecules

import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.correa.unknownword.ui.theme.TypographyRoboto
import com.correa.unknownword.ui.theme.black

@Composable
fun GameTimer(
    progress: Float,
    timeText: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(46.dp)
    ) {
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxHeight()
                .padding(20.dp)
        )
        Text(
            text = timeText,
            style = TypographyRoboto.bodyLarge,
            color = black,
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentHeight(align = Alignment.CenterVertically)
        )
    }
}