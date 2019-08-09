package offline.open.repository

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.Test
import org.threeten.bp.Instant

class TimestampParserTest {

    @Test
    fun verify_timestamp_is_always_converted_properly_based_on_italian_timezone() {
        val initialTimestamp = "2019-07-30 12:08:08"
        val expectedInstant = Instant.ofEpochSecond(1564481288)  // http://timestamp.online/

        assertThat(TimestampParser.extractTimestamp(initialTimestamp))
            .isEqualTo(expectedInstant)
    }
}