package offline.open.common

import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle
import java.util.*

class TimestampFormatter {

    private val formatter = DateTimeFormatter
        .ofLocalizedDateTime(FormatStyle.MEDIUM)
        .withLocale(Locale.ITALY)
        .withZone(ZoneId.systemDefault())

    fun format(timestamp: Instant): String =
        formatter.format(timestamp)
}