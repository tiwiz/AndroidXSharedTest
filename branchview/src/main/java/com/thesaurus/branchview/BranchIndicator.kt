package com.thesaurus.branchview

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout

class BranchIndicator private constructor(app: Application,
                                          private val message: String,
                                          private val configuration: Configuration) : Application.ActivityLifecycleCallbacks by DefaultActivityLifecycleCallbacksDelegate() {

    init {
        app.registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

        val decorView =  activity.window.decorView as ViewGroup

        val messageView = DebugBranchView(activity).apply {
            updateConfiguration(configuration)
            updateMessage(message)
        }
        val params = FrameLayout.LayoutParams(500, 500).apply {
            leftMargin = activity.screenWidth - 500
            topMargin = activity.screenHeight - 500
        }

        decorView.addView(messageView, params)

    }

    companion object {

        fun attachTo(application: Application, message: String, configuration: Configuration = Configuration()) {
            BranchIndicator(application, message, configuration)
        }
    }
}