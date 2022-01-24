package org.techtown.gabojago.randomPick.clock

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.ActivityClockOptionBinding

class ClockOptionActivity : AppCompatActivity() {

    lateinit var binding: ActivityClockOptionBinding
    var timeValue: String = "12"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClockOptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding.clockStartTv.setOnClickListener {
            startActivity(Intent(this, ClockSelectActivity::class.java))
            binding.clockStartTv.text = timeValue
        }
        binding.clockEndTv.setOnClickListener {
            startActivity(Intent(this, ClockSelectActivity::class.java))
            binding.clockEndTv.text = timeValue
        }
        binding.clockCompBtn.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.anim_down, R.anim.anim_none)
        }
    }

    override fun onRestart() {
        super.onRestart()
        var clockSelectActivity = ClockSelectActivity()
        timeValue = clockSelectActivity.pickedNum.toString()
    }
}