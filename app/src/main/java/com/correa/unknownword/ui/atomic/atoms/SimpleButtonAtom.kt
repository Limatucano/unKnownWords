package com.correa.unknownword.ui.atomic.atoms

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.correa.unknownword.ui.theme.disabledColor
import com.correa.unknownword.ui.theme.enabledColor

@Composable
fun SimpleButtonAtom(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int? = null,
    contentDescription: String? = null,
    text: String,
    textStyle: androidx.compose.ui.text.TextStyle,
    isEnabled: Boolean,
    shape: Shape = RoundedCornerShape(8.dp),
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if(isEnabled) enabledColor else disabledColor
        ),
        shape = shape,
        enabled = isEnabled,
    ) {
        if(iconRes != null) {
            Image(
                painterResource(iconRes),
                contentDescription = contentDescription,
            )
        }
        Text(
            text = text,
            style = textStyle,
        )
    }
}