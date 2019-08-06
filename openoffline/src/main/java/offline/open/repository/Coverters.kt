package offline.open.repository

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.threeten.bp.Instant

private val moshi = Moshi.Builder().build()

inline fun <reified T> Moshi.adapter(): JsonAdapter<T> = adapter<T>(T::class.java)

class ListConverter {
    private val adapter: JsonAdapter<List<String>> = moshi.adapter()

    @TypeConverter
    fun toStringValue(v: List<String>): String = v.let { adapter.toJson(v) }

    @TypeConverter
    fun fromString(s: String) = s.let { adapter.fromJson(s) }
}

class InstantConverter {

    @TypeConverter
    fun toLongValue(i: Instant): Long = i.epochSecond

    @TypeConverter
    fun fromLongValue(l: Long) : Instant = Instant.ofEpochSecond(l)
}