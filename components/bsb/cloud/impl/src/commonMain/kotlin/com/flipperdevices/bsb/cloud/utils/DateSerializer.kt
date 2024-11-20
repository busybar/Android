package com.flipperdevices.bsb.cloud.utils

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object DateSerializer : KSerializer<LocalDateTime> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("LocalDateTime", PrimitiveKind.LONG)

    override fun deserialize(decoder: Decoder): LocalDateTime {
        val rawDate = decoder.decodeString()

        return LocalDateTime.Formats.ISO.parse(rawDate)
    }

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        val rawDate = LocalDateTime.Formats.ISO.format(value)
        encoder.encodeString(rawDate)
    }
}
