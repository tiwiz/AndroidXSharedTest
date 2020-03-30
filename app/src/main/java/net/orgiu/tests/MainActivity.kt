package net.orgiu.tests

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import net.orgiu.tests.databinding.ActivityMainBinding
import net.orgiu.tests.main.Functionality
import net.orgiu.tests.main.FunctionalityAdapter
import net.orgiu.tests.main.OnFunctionalityChosenListener
import net.orgiu.tests.main.launchBy
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), OnFunctionalityChosenListener {

    private var binding: ActivityMainBinding by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(toolbar)

        with(binding.functions) {
            layoutManager = LinearLayoutManager(context)
            adapter = FunctionalityAdapter(this@MainActivity)
        }
    }

    override fun onFunctionalityChosen(functionality: Functionality<*>) {
        launchBy(functionality)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
