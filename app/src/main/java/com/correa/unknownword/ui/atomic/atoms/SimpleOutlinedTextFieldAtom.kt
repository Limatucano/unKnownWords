package com.correa.unknownword.ui.atomic.atoms

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.correa.unknownword.ui.theme.black
import com.correa.unknownword.ui.theme.gray

@Composable
fun SimpleOutlinedTextFieldAtom(
    text: String,
    onValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    shape: Shape = RoundedCornerShape(8.dp),
    colors: TextFieldColors? = null,
    isSingleLine: Boolean,
    isEnabled: Boolean = true,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = onValueChanged,
        keyboardOptions = keyboardOptions,
        shape = shape,
        colors = colors ?: TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = black,
            unfocusedIndicatorColor = gray,
            containerColor = Color.White,
            placeholderColor = black,
            textColor = black,
            disabledTextColor = Color.LightGray,
            cursorColor = black,
        ),
        singleLine = isSingleLine,
        enabled = isEnabled,
    )
}