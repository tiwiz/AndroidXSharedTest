package offline.open.common

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import offline.open.BuildConfig


fun Application.initTools() {
    enableFlipper()
}

private fun Application.enableFlipper() {

    SoLoader.init(this, false)

    if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
        with(AndroidFlipperClient.getInstance(this)) {
            val context = this@enableFlipper
            addPlugin(InspectorFlipperPlugin(context, DescriptorMapping.withDefaults()))
            addPlugin(DatabasesFlipperPlugin(context))
            start()
        }
    }
}