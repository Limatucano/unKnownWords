package com.correa.unknownword.ui.game.atomic.molecules

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.correa.unknownword.ui.atomic.atoms.SimpleButtonAtom
import com.correa.unknownword.ui.theme.FinishButton
import com.correa.unknownword.ui.theme.TypographyRoboto
import com.correa.unknownword.ui.theme.background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameHeader(
    roundText: String,
    groupName: String,
    onFinishClicked: () -> Unit
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
                text = roundText,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxHeight()
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
        }
        Text(
            text = groupName,
            style = TypographyRoboto.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxHeight()
                .wrapContentHeight(align = Alignment.CenterVertically)
        )
        SimpleButtonAtom(
            onClick = { onFinishClicked.invoke() },
            text = FinishButton,
            textStyle = TypographyRoboto.labelLarge,
            isEnabled = true,
            modifier = Modifier
                .fillMaxHeight()
        )
    }
}