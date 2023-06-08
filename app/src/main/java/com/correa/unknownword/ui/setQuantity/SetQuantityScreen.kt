package com.correa.unknownword.ui.setQuantity

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier

@Composable
fun SetQuantityScreen(
    viewModel: SetQuantityViewModel,
    onConfirmClicked: (Int) -> Unit
) {

    val state by viewModel.state.observeAsState()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("")
    }
}