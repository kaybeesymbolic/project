package com.placodes.timezone

interface TimeZoneHelper {
    fun getTimeZoneString(): List<String> /* current list of time zone from jetbrains library */
    fun currentTime(): String /* return current time */
    fun currentTimeZone(): String /* return current time zone id */
    fun getTime(otherZoneId: String): String /* return current time */
    fun getDate(otherZoneId: String): String /* return current date */
    fun search(start: Int, end: Int, timezoneStrings: List<String>): List<Int>
    fun hourFromTimeZone(otherZoneId: String): Double
}