package com.correa.unknownword.ui.game.atomic.organims

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.correa.unknownword.ui.theme.TypographyRoboto
import com.correa.unknownword.ui.theme.loadingMessage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameLoadingOrganism(
    time: String?,
    groupName: String?
) {
    Scaffold { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = groupName.loadingMessage(),
                style = TypographyRoboto.titleLarge
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            Text(
                text = time ?: "0",
                style = TypographyRoboto.displayLarge
            )
        }
    }
}