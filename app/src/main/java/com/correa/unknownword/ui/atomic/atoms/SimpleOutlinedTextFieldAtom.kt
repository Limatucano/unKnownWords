package com.correa.unknownword.ui.atomic.atoms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.correa.unknownword.ui.theme.TypographyRoboto
import com.correa.unknownword.ui.theme.black
import com.correa.unknownword.ui.theme.gray

@Composable
fun getColorByState(isError: Boolean) = if(isError) MaterialTheme.colorScheme.error else black

@Composable
fun SimpleOutlinedTextFieldAtom(
    text: String,
    onValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    shape: Shape = RoundedCornerShape(8.dp),
    colors: TextFieldColors? = null,
    isSingleLine: Boolean,
    isEnabled: Boolean = true,
    hintText: String,
    isError: Boolean = false,
    errorMessage: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .padding(
                bottom = if (isError) {
                    0.dp
                } else {
                    10.dp
                }
            )
    ) {
        OutlinedTextField(
            modifier = modifier,
            value = text,
            onValueChange = onValueChanged,
            keyboardOptions = keyboardOptions,
            shape = shape,
            label = {
                Text(hintText)
            },
            colors = colors ?: TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = getColorByState(isError),
                unfocusedIndicatorColor = gray,
                containerColor = Color.White,
                placeholderColor = getColorByState(isError),
                textColor = getColorByState(isError),
                errorLabelColor = MaterialTheme.colorScheme.error,
                errorIndicatorColor = MaterialTheme.colorScheme.error,
                focusedLabelColor = getColorByState(isError),
                unfocusedLabelColor = getColorByState(isError),
                disabledTextColor = Color.LightGray,
                cursorColor = getColorByState(isError),
            ),
            singleLine = isSingleLine,
            enabled = isEnabled,
        )
        if(isError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = TypographyRoboto.labelMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}