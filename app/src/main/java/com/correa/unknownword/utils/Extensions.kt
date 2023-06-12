package com.correa.unknownword.utils

fun Any?.formatToInitialZero() = this?.toString()?.padStart(2,'0') ?: ""