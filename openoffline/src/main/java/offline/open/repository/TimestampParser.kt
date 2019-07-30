package offline.open.repository

import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

object TimestampParser {

    private const val TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss"
    private val formatter = DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT, Locale.ITALIAN)

    fun extractTimestamp(publicationDate: String): Instant =
        LocalDateTime.parse(publicationDate, formatter)
            .atOffset(ZoneOffset.of("+02:00"))
            .toInstant()
}