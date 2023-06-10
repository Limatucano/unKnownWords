package com.correa.unknownword.ui.setQuantity

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.correa.unknownword.ui.setQuantity.atomic.template.SetQuantityTemplate

@Composable
fun SetQuantityScreen(
    viewModel: SetQuantityViewModel,
    onConfirmClicked: (Int) -> Unit
) {

    val state by viewModel.state.observeAsState()

    SetQuantityTemplate(
        state = state,
        onButtonClicked = onConfirmClicked,
        onTextChanged = {
            viewModel.onTextChanged(it)
            viewModel.validateField(it)
        },
        isError = state?.isError ?: false,
        buttonIsEnabled = state?.isError == false && !state?.quantity.isNullOrBlank()
    )
}