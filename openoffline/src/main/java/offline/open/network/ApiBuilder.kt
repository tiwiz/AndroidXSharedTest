package offline.open.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@UnstableDefault
fun buildAPI(url: String): FeedApi =
    Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(jsonFactory())
        .build()
        .create(FeedApi::class.java)

@UnstableDefault
private fun jsonFactory() =
    Json.nonstrict.asConverterFactory("application/json".toMediaType())

private fun okHttpClient() =
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor())
        .build()

fun loggingInterceptor() =
    HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
