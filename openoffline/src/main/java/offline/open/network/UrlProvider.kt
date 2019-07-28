package offline.open.network

import offline.open.BuildConfig.*
import java.net.URLEncoder

object UrlProvider {

    private val encodedFeedUrl = URLEncoder.encode(FEED_URL, "UTF-8")

    val apiUrl = "$API_PATH?rss_url=$encodedFeedUrl&api_key=$RSS2JSON"

}