package net.orgiu.tests.nightmode

import androidx.appcompat.app.AppCompatDelegate

enum class NightModeType(val type: Int, val flag: Int) {
    FOLLOW_SYSTEM(0, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM),
    FORCE_LIGHT(1, AppCompatDelegate.MODE_NIGHT_NO),
    FORCE_DARK(2, AppCompatDelegate.MODE_NIGHT_YES);

    companion object {
        fun fromType(type: Int) =
            when (type) {
                0 -> FOLLOW_SYSTEM
                1 -> FORCE_LIGHT
                else -> FORCE_DARK
            }
    }

}