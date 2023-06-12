package com.correa.unknownword.ui.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.correa.unknownword.data.model.ResultModel
import com.correa.unknownword.ui.atomic.atoms.SimpleButtonAtom
import com.correa.unknownword.ui.theme.ContinueButton
import com.correa.unknownword.ui.theme.TypographyRoboto

@Composable
fun getResultDataByStatus(
    isWin: Boolean,
    currentGroupName: String,
    nextRound: Int?,
) : ResultModel{
    return when(isWin) {
        true -> ResultModel.WinResult(
            groupName = currentGroupName,
            nextRound = nextRound?.plus(1),
            isLastWin = nextRound == null
        )
        false -> ResultModel.LoseResult(
            groupName = currentGroupName,
        )
    }
}

@Composable
fun ResultDialog(
    currentGroupName: String,
    isWin: Boolean,
    nextRound: Int?,
    onButtonClicked: (Boolean) -> Unit
) {
    val result = getResultDataByStatus(
        isWin = isWin,
        currentGroupName = currentGroupName,
        nextRound = nextRound
    )

    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(48.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painterResource(result.iconRes),
                    contentDescription = "",
                    modifier = Modifier
                        .height(64.dp)
                        .width(64.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = result.title,
                    style = TypographyRoboto.labelLarge,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = result.subtitle,
                    style = TypographyRoboto.labelMedium
                )
                Spacer(modifier = Modifier.height(12.dp))
                SimpleButtonAtom(
                    onClick = { onButtonClicked(false) },
                    text = ContinueButton,
                    isEnabled = true,
                    textStyle = TypographyRoboto.labelLarge,
                )
            }
        }
    }
}