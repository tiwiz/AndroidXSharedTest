package net.orgiu.tests.nightmode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import net.orgiu.tests.R
import net.orgiu.tests.databinding.ActivityNightModeBinding

class NightModeActivity : AppCompatActivity() {

    private val nightModeStorage by lazy {
        NightModeStorage(this)
    }

    private val contextWrapper by lazy {
        ContextWrapper(this)
    }

    private lateinit var binding : ActivityNightModeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNightModeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.restart.setOnClickListener {
            storeThemeChoice()
            storeContextChoice()
            recreate()
        }

        loadDrawable()
    }

    private fun storeContextChoice() {
        val contextType = if (binding.contextGroup.checkedRadioButtonId == R.id.raioActivity) {
            ImageContext.ACTIVITY
        } else {
            ImageContext.APPLICATION
        }

        nightModeStorage.store(contextType)
    }

    private fun storeThemeChoice() {
        val type = when (binding.themeGroup.checkedRadioButtonId) {
            R.id.followSystem -> NightModeType.FOLLOW_SYSTEM
            R.id.forceLight -> NightModeType.FORCE_LIGHT
            else -> NightModeType.FORCE_DARK
        }

        nightModeStorage.store(type)
    }

    private fun loadDrawable() {
        val drawable = contextWrapper.getDrawable(R.drawable.ic_adb)
        binding.sampleImage.setImageDrawable(drawable)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.let {
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

        binding.themeGroup.check(id)

        val contextId = when(nightModeStorage.fetchContext()) {
            ImageContext.APPLICATION -> R.id.radioApplication
            else -> R.id.raioActivity
        }

        binding.contextGroup.check(contextId)
    }
}
