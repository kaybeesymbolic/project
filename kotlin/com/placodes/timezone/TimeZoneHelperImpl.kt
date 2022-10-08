package com.placodes.timezone



import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.TimeZone

object TimeZoneHelperImpl: TimeZoneHelper {
    override fun getTimeZoneString(): List<String> = TimeZone.availableZoneIds.sorted()

    /** get local date time and then format it using the function below */
    override fun currentTime(): String = getLocalDateTime { formatDateTime(it) }

    override fun currentTimeZone(): String = TimeZone.currentSystemDefault().toString()

    /** use the other timezone id to get local date and time and then format it to stirng */
    override fun getTime(otherZoneId: String): String = getLocalDateTime(otherZoneId){ formatDateTime(it) }


    override fun getDate(otherZoneId: String): String {
        val dateTime = getLocalDateTime(otherZoneId)
        return """
            ${dateTime.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }}, 
            ${dateTime.month.name.lowercase().replaceFirstChar { it.uppercase() }} 
            ${dateTime.date.dayOfMonth}
              """.trimIndent()
    }

    override fun hourFromTimeZone(otherZoneId: String): Double {
        val currentLocalDateTime = getLocalDateTime()
        val otherLocalDateTime = getLocalDateTime(otherZoneId)
        val current = getDateTimePeriod(currentLocalDateTime)
        val other = getDateTimePeriod(otherLocalDateTime)
        return kotlin.math.abs((current.hours - other.hours) * 1.0)
        //return abs((currentLocalDateTime.hour - otherLocalDateTime.hour)*1.0)
    }

    override fun search(start: Int, end: Int, timezoneStrings: List<String>): List<Int> {
        val validHours = mutableListOf<Int>()
        val hoursRange = hours(start,end)
        for (hour in hoursRange){
            if (!validHour(hour, hoursRange)) continue

            timezoneStrings.forEach{
                val visibleDateTime= userDateTime(otherZoneId = it)
                validHours.add(visibleDateTime.hour)
            }
        }
        return validHours
    }
}
