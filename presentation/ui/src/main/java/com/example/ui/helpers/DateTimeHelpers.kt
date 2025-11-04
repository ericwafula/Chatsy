package com.example.ui.helpers

import java.time.Clock
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

import java.time.temporal.ChronoUnit
import kotlin.math.abs

/**
 * Turns a LocalDateTime into a relative string like:
 * "just now", "3 minutes ago", "in 2 hours", "yesterday", etc.
 *
 * @param zone  Zone for interpreting the LocalDateTime (defaults to device zone)
 * @param clock For testing or time-travel (defaults to system clock)
 */
fun LocalDateTime.toRelativeTime(
    zone: ZoneId = ZoneId.systemDefault(),
    clock: Clock = Clock.system(zone)
): String {
    val now = ZonedDateTime.now(clock)
    val then = this.atZone(zone)

    // Handle null/invalid edge cases if needed outside
    val future = then.isAfter(now)

    val start = if (future) now else then
    val end   = if (future) then else now

    // Try calendar-aware units first (years/months/days)
    val years = ChronoUnit.YEARS.between(start, end)
    if (years >= 1) return quantity(years, future, "year")

    val months = ChronoUnit.MONTHS.between(start, end)
    if (months >= 1) return quantity(months, future, "month")

    val days = ChronoUnit.DAYS.between(start, end)
    if (days >= 7) {
        val weeks = days / 7
        return quantity(weeks, future, "week")
    }
    if (days >= 2) return quantity(days, future, "day")
    if (days == 1L) return if (future) "tomorrow" else "yesterday"

    // Sub-day uses Duration for precision
    val dur = Duration.between(start, end)
    val hours = dur.toHours()
    if (hours >= 1) return quantity(hours, future, "hour")

    val minutes = dur.toMinutes()
    if (minutes >= 1) return quantity(minutes, future, "minute")

    val seconds = dur.seconds
    if (seconds >= 5) return quantity(seconds, future, "second")

    // < 5 seconds
    return if (future) "in a few seconds" else "just now"
}

private fun quantity(n: Long, future: Boolean, unit: String): String {
    val absN = abs(n)
    val u = if (absN == 1L) unit else unit + "s"
    return if (future) "in $absN $u" else "$absN $u ago"
}
