package com.correa.unknownword.domain.entities

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.correa.unknownword.R
import com.correa.unknownword.ui.theme.background
import com.correa.unknownword.ui.theme.black
import com.correa.unknownword.ui.theme.green
import com.correa.unknownword.ui.theme.red

data class WordEntity(
    val word: String,
    var wordStatus: WordStatus = WordStatus.DEFAULT
)

enum class WordStatus(
    @DrawableRes val iconRes: Int,
    val color: Color,
    val textColor: Color
) {

    DEFAULT(
        iconRes = R.drawable.ic_default,
        color = background,
        textColor = black,
    ),
    RIGHT(
        iconRes = R.drawable.ic_right,
        color = green,
        textColor = background
    ),
    WRONG(
        iconRes = R.drawable.ic_wrong,
        color = red,
        textColor = background
    )
}
