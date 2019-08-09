package net.orgiu.weightwatcher

import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle
import java.util.*

private val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
    .withLocale(Locale.ITALY)
    .withZone(ZoneId.systemDefault())

@Serializer(forClass = Instant::class)
object InstantSerializer : KSerializer<Instant> {
    override val descriptor: SerialDescriptor
        get() = StringDescriptor.withName("WithCustomDefault")

    override fun deserialize(decoder: Decoder): Instant =
        Instant.parse(decoder.decodeString())

    override fun serialize(encoder: Encoder, obj: Instant) {
        encoder.encodeString("$obj")
    }
}

@Serializable
data class Weight(
    val value: Float,
    @Serializable(with = InstantSerializer::class) val timestamp: Instant
)

data class UiModule(
    val weight: String,
    val timestamp: String
)

fun Weight.toUiModule() =
    UiModule(weight = "$value KG", timestamp = formatter.format(timestamp))