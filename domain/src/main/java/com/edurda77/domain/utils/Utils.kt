package com.edurda77.domain.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun convertStringToLocalDate(date: String): LocalDate {
    val format = LocalDate.Format {
        year()
        char('-')
        monthNumber()
        char('-')
        day(padding = Padding.ZERO)
    }

    return format.parseOrNull(date)?:  Clock.System.now()
        .toLocalDateTime(
            TimeZone.currentSystemDefault()
        ).date
}


fun Char.isAllowed(): Boolean {
    return when {
        this in 'a'..'z' -> true
        this in 'A'..'Z' -> true
        this in '0'..'9' -> true
        this == '@' -> true
        this == '.' -> true
        else -> false
    }
}