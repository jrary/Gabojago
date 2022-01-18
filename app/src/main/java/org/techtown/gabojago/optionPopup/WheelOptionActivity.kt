package org.techtown.gabojago.optionPopup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.techtown.gabojago.Record.CalendarActivity
import org.techtown.gabojago.databinding.ActivityWheelOptionBinding

class WheelOptionActivity : AppCompatActivity() {

    lateinit var binding: ActivityWheelOptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWheelOptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.wheelComp2Btn.setOnClickListener {
            startActivity(Intent(this@WheelOptionActivity, WheelSelectActivity::class.java))
        }

        binding.wheelCompBtn.setOnClickListener {
            finish()
        }
    }
}