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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_night_mode)

        setSupportActionBar(toolbar)

        restart.setOnClickListener {
            val type = when(group.checkedRadioButtonId) {
                R.id.followSystem -> NightModeType.FOLLOW_SYSTEM
                R.id.forceLight -> NightModeType.FORCE_LIGHT
                else -> NightModeType.FORCE_DARK
            }

            nightModeStorage.store(type)

            recreate()
        }
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
        item.icon = resources.getDrawable(R.drawable.ic_adb)
    }

    override fun onStart() {
        super.onStart()

        val id = when(nightModeStorage.fetch()) {
            NightModeType.FOLLOW_SYSTEM -> R.id.followSystem
            NightModeType.FORCE_LIGHT -> R.id.forceLight
            else -> R.id.forceDark
        }

        group.check(id)
    }
}
