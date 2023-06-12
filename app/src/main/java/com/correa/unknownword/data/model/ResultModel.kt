package com.correa.unknownword.data.model

import androidx.annotation.DrawableRes
import com.correa.unknownword.R

sealed class ResultModel(
    @DrawableRes val iconRes: Int,
    val title: String,
    val subtitle: String
) {

    data class WinResult(
        val groupName: String,
        val nextRound: Int?,
        val isLastWin: Boolean
    ): ResultModel(
        iconRes = R.drawable.happy_emoji,
        title = "$groupName, parabéns!!",
        subtitle = if(isLastWin) "Você conseguiu! Virou milionário." else "Seu próximo round será: $nextRound"
    )

    data class LoseResult(
        val groupName: String,
    ) : ResultModel(
        iconRes = R.drawable.sad_emoji,
        title = "$groupName, vocês já foram melhores em.",
        subtitle = "Tentem se concentrar na próxima rodada, ou vão tomar um piau."
    )
}
