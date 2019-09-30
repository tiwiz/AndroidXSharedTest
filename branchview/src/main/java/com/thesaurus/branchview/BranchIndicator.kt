package com.thesaurus.branchview

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout

class BranchIndicator private constructor(
    app: Application,
    private val defaultMessage: String,
    private val defaultConfig: Configuration
) : Application.ActivityLifecycleCallbacks by DefaultActivityLifecycleCallbacksDelegate() {

    init {
        app.registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {


        val config = provideConfig(activity)

        if (config.shouldShow) {
            showUi(activity, config)
        }

    }

    private fun showUi(activity: Activity, config: Configuration) {

        val decorView = activity.window.decorView as ViewGroup

        val messageView = DebugBranchView(activity).apply {
            updateConfiguration(config)
            updateMessage(provideMessage(activity))
        }

        val params = FrameLayout.LayoutParams(activity.screenWidth, 500).apply {
            topMargin = activity.screenHeight - 500
        }

        decorView.addView(messageView, params)
    }

    private fun provideConfig(activity: Activity) =
        if (activity is ConfigurationProvider) {
            activity.provideConfiguration()
        } else {
            defaultConfig
        }

    private fun provideMessage(activity: Activity) =
        if (activity is MessageProvider) {
            activity.provideMessage()
        } else {
            defaultMessage
        }

    companion object {

        fun attachTo(
            application: Application,
            message: String,
            configuration: Configuration = Configuration()
        ) {
            BranchIndicator(application, message, configuration)
        }
    }
}