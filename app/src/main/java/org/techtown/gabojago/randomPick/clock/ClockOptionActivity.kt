package org.techtown.gabojago.randomPick.clock

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import org.techtown.gabojago.databinding.ActivityClockOptionBinding

class ClockOptionActivity : AppCompatActivity() {

    lateinit var binding: ActivityClockOptionBinding

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
        }
        binding.clockEndTv.setOnClickListener {
            startActivity(Intent(this, ClockSelectActivity::class.java))
        }
        binding.clockCompBtn.setOnClickListener {
            finish()
        }
    }
}