package org.techtown.gabojago.randomPick.clock

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.ActivityClockOptionBinding

class ClockOptionActivity : AppCompatActivity() {

    lateinit var binding: ActivityClockOptionBinding
    var pickedNum: String = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClockOptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val clockSelect = ClockSelectActivity()
        binding.clockStartTv.setOnClickListener {
            startActivity(Intent(this, ClockSelectActivity::class.java))
            clockSelect.setMyTimeClickListener(object : ClockSelectActivity.MyTimeClickListener {
                override fun onTimeClick() {
                    binding.clockEndTv.text = clockSelect.pNum
                }
            })
        }
        binding.clockEndTv.setOnClickListener {
            startActivity(Intent(this, ClockSelectActivity::class.java))
            clockSelect.setMyTimeClickListener(object : ClockSelectActivity.MyTimeClickListener {
                override fun onTimeClick() {
                    binding.clockEndTv.text = pickedNum
                }
            })
        }
        binding.clockCompBtn.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.anim_down, R.anim.anim_none)
        }

    }

    override fun onResume() {
        super.onResume()

    }
}