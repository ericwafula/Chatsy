package com.example.domain.helpers

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

val CurrentZoneId: ZoneId = ZoneId.systemDefault()

val String.asInstant: Instant
    get() = Instant.parse(this)

val Instant.asLocalDateTime: LocalDateTime
    get() = LocalDateTime.ofInstant(this, CurrentZoneId)
