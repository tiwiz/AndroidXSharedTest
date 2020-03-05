package net.orgiu.tests.nightmode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_night_mode.*
import net.orgiu.tests.R

class NightModeActivity : AppCompatActivity() {

    private val nightModeStorage by lazy {
        NightModeStorage(this)
    }

    private val contextWrapper by lazy {
        ContextWrapper(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_night_mode)

        setSupportActionBar(toolbar)

        restart.setOnClickListener {
            storeThemeChoice()
            storeContextChoice()
            recreate()
        }

        loadDrawable()
    }

    private fun storeContextChoice() {
        val contextType = if (contextGroup.checkedRadioButtonId == R.id.raioActivity) {
            ImageContext.ACTIVITY
        } else {
            ImageContext.APPLICATION
        }

        nightModeStorage.store(contextType)
    }

    private fun storeThemeChoice() {
        val type = when (themeGroup.checkedRadioButtonId) {
            R.id.followSystem -> NightModeType.FOLLOW_SYSTEM
            R.id.forceLight -> NightModeType.FORCE_LIGHT
            else -> NightModeType.FORCE_DARK
        }

        nightModeStorage.store(type)
    }

    private fun loadDrawable() {
        val drawable = contextWrapper.getDrawable(R.drawable.ic_adb)
        sampleImage.setImageDrawable(drawable)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.let {
            addIconTo(menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun addIconTo(menu: Menu) {
        val item = menu.add(0, 0, 0, "TEST")

        item.isVisible = true
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        item.icon = contextWrapper.getDrawable(R.drawable.ic_adb)
    }

    override fun onStart() {
        super.onStart()

        val id = when(nightModeStorage.fetchNightMode()) {
            NightModeType.FOLLOW_SYSTEM -> R.id.followSystem
            NightModeType.FORCE_LIGHT -> R.id.forceLight
            else -> R.id.forceDark
        }

        themeGroup.check(id)

        val contextId = when(nightModeStorage.fetchContext()) {
            ImageContext.APPLICATION -> R.id.radioApplication
            else -> R.id.raioActivity
        }

        contextGroup.check(contextId)
    }
}
