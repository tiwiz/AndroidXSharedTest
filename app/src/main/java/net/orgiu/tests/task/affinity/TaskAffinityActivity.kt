package net.orgiu.tests.task.affinity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.orgiu.tests.R
import net.orgiu.tests.databinding.ActivityTaskAffinityBinding
import kotlin.properties.Delegates
import kotlin.random.Random.Default.nextInt

class TaskAffinityActivity : AppCompatActivity() {

    private var binding : ActivityTaskAffinityBinding by Delegates.notNull()
    private val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTaskAffinityBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnNewActivity.setOnClickListener {
            startActivity(Intent(this, TaskAffinityActivity::class.java))
        }

        binding.btnCloseAffinity.setOnClickListener {
            finishAffinity()
        }

        binding.textView.text = generateRandomText()
    }

    private fun generateRandomText(): CharSequence {
        val length = nextInt(0, 1000)
        return (1..length)
            .map { nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }
}