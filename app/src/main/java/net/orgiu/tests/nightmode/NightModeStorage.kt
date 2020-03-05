package net.orgiu.tests.nightmode

import android.content.Context
import android.preference.PreferenceManager.getDefaultSharedPreferences
import androidx.core.content.edit

class NightModeStorage(context: Context) {

    private val storage = getDefaultSharedPreferences(context)

    fun store(type: NightModeType) {
        storage.edit {
            putInt(KEY, type.type)
        }
    }

    fun fetch() = NightModeType.fromType(storage.getInt(KEY, 0))

    companion object {
        private const val KEY = "NIGHT_MODE"
    }
}