package net.orgiu.tests.nightmode

import android.content.Context
import android.preference.PreferenceManager.getDefaultSharedPreferences
import androidx.core.content.edit

class NightModeStorage(context: Context) {

    private val storage = getDefaultSharedPreferences(context)

    fun store(type: NightModeType) {
        storage.edit {
            putInt(NIGHT_MODE_KEY, type.type)
        }
    }

    fun store(type: ImageContext) {
        storage.edit {
            putInt(CONTEXT_KEY, type.type)
        }
    }

    fun fetchNightMode() = NightModeType.fromType(storage.getInt(NIGHT_MODE_KEY, 0))

    fun fetchContext() = ImageContext.fromType(storage.getInt(CONTEXT_KEY, 1))

    companion object {
        private const val NIGHT_MODE_KEY = "NIGHT_MODE"
        private const val CONTEXT_KEY = "CONTEXT"
    }
}