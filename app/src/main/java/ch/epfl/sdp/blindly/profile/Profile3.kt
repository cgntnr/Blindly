package ch.epfl.sdp.blindly.profile

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import ch.epfl.sdp.blindly.R
import java.time.LocalDate
import java.time.Period

const val EXTRA_BIRTHDAY = "age"
class Profile3 : AppCompatActivity() {

    private val MAJORITY_AGE = 18
    private var username: String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.set_profile_3)
        username = intent.getStringExtra(EXTRA_USERNAME)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun startProfile4(view: View) {
        findViewById<TextView>(R.id.warning_p3).visibility = View.INVISIBLE
        val datePicker: DatePicker = findViewById<View>(R.id.datePicker) as DatePicker
        val day: Int = datePicker.dayOfMonth
        val month: Int = datePicker.month + 1 //month correction
        val year: Int = datePicker.year
        val age = getAge(year, month, day)

        if (age < MAJORITY_AGE) {
            findViewById<TextView>(R.id.warning_p3).visibility = View.VISIBLE
        } else {
            val ageExtra = "$day.$month.$year"
            val extras = Bundle()
            extras.putString(EXTRA_USERNAME, username)
            extras.putString(EXTRA_BIRTHDAY, ageExtra)
            val intent = Intent(this, Profile4::class.java)
            intent.putExtras(extras)
            startActivity(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAge(year: Int, month: Int, dayOfMonth: Int): Int {
        return Period.between(
            LocalDate.of(year, month, dayOfMonth),
            LocalDate.now()
        ).years
    }


}