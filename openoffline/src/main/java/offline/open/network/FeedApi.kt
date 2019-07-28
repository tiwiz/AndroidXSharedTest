package offline.open.network

import offline.open.models.OpenFeed
import retrofit2.http.GET

interface FeedApi {

    @GET(UrlProvider.apiUrl)
    suspend fun fetch(): OpenFeed
}