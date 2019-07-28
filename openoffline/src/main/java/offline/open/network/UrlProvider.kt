package offline.open.network

import offline.open.BuildConfig.*

object UrlProvider {

    const val apiUrl = "$API_PATH?rss_url=$FEED_URL&api_key=$RSS2JSON"

}