package com.correa.unknownword.utils

import java.util.concurrent.TimeUnit

object Constants {
    const val MAX_ROUND = 10
    const val MAX_TIME_PER_ROUND = 90000L
    private const val TIME_FORMAT = "%02d:%02d"


    //convert time to milli seconds
    fun Long.formatTime(): String = String.format(
        TIME_FORMAT,
        TimeUnit.MILLISECONDS.toMinutes(this),
        TimeUnit.MILLISECONDS.toSeconds(this) % 60
    )
}