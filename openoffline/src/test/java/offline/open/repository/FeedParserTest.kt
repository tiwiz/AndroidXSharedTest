package offline.open.repository

import assertk.assertThat
import assertk.assertions.containsOnly
import org.junit.Test

class FeedParserTest {

    private val wrapper = StyleWrapper()
    private val parser = FeedParser(wrapper)

    @Test
    fun verify_items_are_converted_properly() {
        val parsed = parser.parse(SampleData.openFeed)
        val cleanContent = SampleData.dbArticle1.content
        val expected = SampleData.dbArticle1.copy(content = wrapper.wrapIntoStyle(cleanContent))

        assertThat(parsed).containsOnly(expected)
    }
}