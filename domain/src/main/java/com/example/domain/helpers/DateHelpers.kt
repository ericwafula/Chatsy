package com.example.domain.helpers

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

const val ISO_8601 = "YYYY-MM-DDTHH:mm:ssZ"

val CurrentZoneId: ZoneId = ZoneId.systemDefault()

val String.asInstant: Instant
    get() = Instant.parse(this)

val Instant.asLocalDateTime: LocalDateTime
    get() = LocalDateTime.ofInstant(this, CurrentZoneId)

val Now: LocalDateTime
    get() = LocalDateTime.now()

val LocalDateTime.asString: String
    get() = DateTimeFormatter.ofPattern(ISO_8601).format(this)
