package common

import java.time.LocalDate

fun String.isValidCategory():Boolean{
    return this.length in 3..10 && this.contains("^[a-zA-Z]+$".toRegex())
}

fun parseDate(input: String): LocalDate {
    val parts = input.split("-")
    if (parts.size != 3) throw IllegalArgumentException("Invalid date format")

    val year = parts[0]
    val month = parts[1].padStart(2, '0')
    val day = parts[2].padStart(2, '0')

    val formatted = "$year-$month-$day"
    return LocalDate.parse(formatted)
}

