package org.techtown.gabojago.randomPick.clock

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.ActivityClockOptionBinding

class ClockOptionActivity : AppCompatActivity() {

    lateinit var binding: ActivityClockOptionBinding
    private var pickedNum: String = "0"
    var state: Boolean = true
    var clockFragment = ClockFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClockOptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding.clockStartTv.setOnClickListener {
            startActivityForResult(Intent(this, ClockSelectActivity::class.java), 100)
            state = true
        }
        binding.clockEndTv.setOnClickListener {
            startActivityForResult(Intent(this, ClockSelectActivity::class.java), 100)
            state = false
        }
        binding.clockCompBtn.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.anim_down, R.anim.anim_none)
        }

    }

    override fun onStart() {
        super.onStart()
        binding.clockStartTv.text = clockFragment.startNum.toString()
        binding.clockEndTv.text = clockFragment.endNum.toString()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == 100){
            pickedNum = data?.getStringExtra("clock")!!
            if(state){
                binding.clockStartTv.text = pickedNum
                clockFragment.updateStartClock(pickedNum.toInt())
            }
            else{
                binding.clockEndTv.text = pickedNum
                clockFragment.updateEndClock(pickedNum.toInt())
            }
        }
    }
}