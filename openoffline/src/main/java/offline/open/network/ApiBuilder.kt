package offline.open.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

fun buildAPI(url: String) =
    Retrofit.Builder()
        .baseUrl(url)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()
        .create(FeedApi::class.java)