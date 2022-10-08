package com.placodes.timezone

import kotlinx.datetime.*
import kotlin.math.max
import kotlin.math.min


/** this function is used to format local date time */
fun formatDateTime(datetime: LocalDateTime):String{
    val dates = datetime.date
    val builder = StringBuffer()
    val hours = datetime.hour
    val minutes = datetime.minute
    val sufix = if(hours > 12) " pm" else " am"
    builder.append(dates.toString().plus(" "))
    builder.append(hours.toString())
    builder.append(":")
    if(minutes < 10) builder.append("0")
    builder.append(minutes.toString())
    builder.append(sufix)
    return builder.toString()
}


fun getLocalDateTime(timeZoneId: String?= null): LocalDateTime {
    val timezone = TimeZone.of(timeZoneId?: TimeZone.currentSystemDefault().toString())
    val current = Clock.System.now()
    val localDateTime = current.toLocalDateTime(timezone)
    return localDateTime
}
/** get local date time */
fun getLocalDateTime(timeZoneId: String? = null, formatToString: (value : LocalDateTime)->String): String {
    val timezone = TimeZone.of(timeZoneId?: TimeZone.currentSystemDefault().toString())
    val currentMoment = Clock.System.now()
    val localDateTime =  currentMoment.toLocalDateTime(timezone)
    return formatToString(localDateTime)
}

/** get date time period */
fun getDateTimePeriod(localDateTime: LocalDateTime): DateTimePeriod {
    val hour = localDateTime.hour
    val minute = localDateTime.minute
    val second = localDateTime.second
    val days = localDateTime.dayOfWeek.value
    val month = localDateTime.dayOfMonth
    val year = localDateTime.year
    return DateTimePeriod(
        years = year,
        months = month,
        days = days,
        hours = hour,
        minutes = minute,
        seconds = second,
        nanoseconds = 0

    )
}

/** valid 24-hours ranges */
fun hours(start: Int,end: Int) = IntRange(max(0, start), min(23, end))
/** evaluate if time fall within 24 hours period */
fun validHour(hour: Int, hours: IntRange): Boolean = hour in hours

/** get others date and time */
fun othersDateTime(otherZoneId: String): LocalDateTime = getLocalDateTime(otherZoneId)

/** get corresponding user's or current date and time */
fun userDateTime(
    currentZone: TimeZone = TimeZone.currentSystemDefault(),
    otherZoneId: String
): LocalDateTime =
    getLocalDateTime(otherZoneId).toInstant(currentZone).toLocalDateTime(TimeZone.of(otherZoneId))