package offline.open.network

import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertThat
import assertk.assertions.isEqualTo
import offline.open.BuildConfig
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UrlProviderTest {

    private val expectedRoot =
        "/v1/api.json?" +
                "rss_url=https%3A%2F%2Fwww.open.online%2Ffeed%2F&" +
                "api_key=${BuildConfig.RSS2JSON}"

    @Test
    fun verify_feed_url_is_urlencoded() {
        assertThat(UrlProvider.apiUrl).isEqualTo(expectedRoot)
    }
}