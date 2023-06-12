package com.correa.unknownword.utils

import java.util.concurrent.TimeUnit

object Constants {
    const val MAX_ROUND = 6
    const val INITIAL_WORDS = 10
    const val MAX_TIME_PER_ROUND = 90000L
    const val INITIAL_TIME = 6000L
    const val INTERVAL_TIME = 1000L
    const val TIME_FORMAT = "%02d:%02d"
    const val NEED_TO_RIGHT = 5
    const val SECONDS_TIME_FORMAT = "%02d"


    //convert time to milli seconds
    fun Long.formatTime(): String = String.format(
        TIME_FORMAT,
        TimeUnit.MILLISECONDS.toMinutes(this),
        TimeUnit.MILLISECONDS.toSeconds(this) % 60
    )
}