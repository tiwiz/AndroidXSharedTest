package offline.open.repository

import androidx.room.TypeConverter
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import kotlinx.serialization.serializer
import org.threeten.bp.Instant


@UnstableDefault
class ListConverter {

    @TypeConverter
    fun toStringValue(v: List<String>): String = v.let { Json.stringify(String.serializer().list, v) }

    @TypeConverter
    fun fromString(s: String) = s.let { Json.parse(String.serializer().list, s) }
}

class InstantConverter {

    @TypeConverter
    fun toLongValue(i: Instant): Long = i.epochSecond

    @TypeConverter
    fun fromLongValue(l: Long) : Instant = Instant.ofEpochSecond(l)
}