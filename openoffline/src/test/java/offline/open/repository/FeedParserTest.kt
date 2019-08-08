package offline.open.repository

import assertk.assertThat
import assertk.assertions.containsOnly
import org.junit.Test

class FeedParserTest {

    private val parser = FeedParser()

    @Test
    fun verify_items_are_converted_properly() {
        val parsed = parser.parse(SampleData.openFeed)
        val expected = SampleData.dbArticle1

        assertThat(parsed).containsOnly(expected)
    }
}