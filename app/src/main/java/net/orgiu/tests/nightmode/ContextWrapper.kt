package net.orgiu.tests.nightmode

import android.app.Activity
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes

class ContextWrapper(private val activity: Activity) {

    private val nightModeStorage by lazy {
        NightModeStorage(activity)
    }

    fun getDrawable(@DrawableRes id: Int) : Drawable =
        if (nightModeStorage.fetchContext() == ImageContext.APPLICATION) {
            activity.application.resources.getDrawable(id, activity.application.theme)
        } else {
            activity.getDrawable(id)
        }
}