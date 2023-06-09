package com.correa.unknownword.ui.setQuantity.atomic.template

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.correa.unknownword.ui.atomic.atoms.SimpleButtonAtom
import com.correa.unknownword.ui.atomic.atoms.SimpleOutlinedTextFieldAtom
import com.correa.unknownword.ui.setQuantity.SetQuantityState
import com.correa.unknownword.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetQuantityTemplate(
    state: SetQuantityState?,
    onTextChanged: (String) -> Unit,
    onButtonClicked: (Int) -> Unit,
    isError: Boolean,
) {
    Scaffold(
        containerColor = Color(0xFFEDE8E3)
    ) { _ ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize(3F),
        ) {
            Text(
                text = setQuantityTitle,
                color = Color(0xFF060710),
                style = TypographyRoboto.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(36.dp))
            SimpleOutlinedTextFieldAtom(
                text = state?.quantity ?: "",
                onValueChanged = onTextChanged,
                isSingleLine = true,
                hintText = setQuantityHint,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingValues(horizontal = 20.dp)),
                errorMessage = setQuantityErrorMessage,
                isError = isError,
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxSize(1F),
        ) {
            SimpleButtonAtom(
                onClick = { onButtonClicked(state?.quantity?.toInt() ?: 0) },
                text = ContinueButton,
                textStyle = TypographyRoboto.labelLarge,
                isEnabled = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(PaddingValues(horizontal = 20.dp))
            )
        }
    }
}