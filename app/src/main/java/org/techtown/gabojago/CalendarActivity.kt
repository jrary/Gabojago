package org.techtown.gabojago

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.techtown.gabojago.databinding.ActivityCalendarBinding

class CalendarActivity :AppCompatActivity() {
    lateinit var binding: ActivityCalendarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

    }
}