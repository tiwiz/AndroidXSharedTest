package net.orgiu.weightwatcher

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.michaldrabik.classicmaterialtimepicker.CmtpDialogFragment
import com.michaldrabik.classicmaterialtimepicker.utilities.setOnTime24PickedListener
import com.vivekkaushik.datepicker.OnDateSelectedListener
import kotlinx.android.synthetic.main.activity_weight_record.*
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset

class WeightRecordActivity : AppCompatActivity() {

    private val weightRepository by lazy { WeightRepository(this) }

    private var selectedDate = LocalDateTime.now()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight_record)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
    }

    private fun initViews() {
        val now = LocalDateTime.now()

        with(datePicker) {
            setInitialDate(now.year, now.monthValue, now.dayOfMonth)
            setOnDateSelectedListener(object : OnDateSelectedListener {
                override fun onDateSelected(year: Int, month: Int, day: Int, dayOfWeek: Int) {
                    selectedDate = selectedDate.withYear(year)
                        .withMonth(month)
                        .withDayOfMonth(day)
                }

                override fun onDisabledDateSelected(
                    year: Int,
                    month: Int,
                    day: Int,
                    dayOfWeek: Int,
                    isDisabled: Boolean
                ) {
                }
            })
        }
        with(hourPicker) {
            updateTime(now.hour, now.minute)
            setOnClickListener {
                showTimePicker(now)
            }
        }

        btnSave.setOnClickListener {
            saveData()
            finish()
        }
    }

    private fun showTimePicker(now: LocalDateTime) {
        CmtpDialogFragment.newInstance().apply {
            setInitialTime24(now.hour, now.minute)
            setOnTime24PickedListener {
                updateTime(it.hour, it.minute)
            }
        }.show(supportFragmentManager, "TimePickerDialog")
    }

    private fun updateTime(hours: Int, minutes: Int) {
        val formattedText = "$hours:$minutes"
        hourPicker.text = formattedText
    }

    private fun saveData() {
        val weight = weightView.value.toFloat() / 100.toFloat()
        val (hours, minutes) = hourPicker.time()
        selectedDate = selectedDate.withHour(hours).withMinute(minutes)

        weightRepository.addWeight(Weight(weight, selectedDate.toInstant(ZoneOffset.UTC)))
    }

    private fun TextView.time(): Pair<Int, Int> {
        val items = text.split(":").map { it.toInt() }
        return items[0] to items[1]
    }
}
