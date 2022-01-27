package org.techtown.gabojago.randomPick.clock

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.ActivityClockOptionBinding

class ClockOptionActivity : AppCompatActivity() {

    lateinit var binding: ActivityClockOptionBinding
    var clockFragment = ClockFragment() //쓰레기!!!!♥♥♥♥♥♥♥♥♥♥♥♥
    var startNum: Int = 12
    var endNum: Int = 12

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
        }
        binding.clockEndTv.setOnClickListener {
            startActivityForResult(Intent(this, ClockSelectActivity::class.java), 101)
        }
        binding.clockCompBtn.setOnClickListener {
            var intent = Intent()
            intent.putExtra("start", startNum)
            intent.putExtra("end", endNum)
            setResult(RESULT_OK, intent)
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
        if(requestCode == 100){
            startNum = data?.getIntExtra("clock", 12)!!
            binding.clockStartTv.text = startNum.toString()
        }
        else if(requestCode == 101){
            endNum = data?.getIntExtra("clock", 12)!!
            binding.clockEndTv.text = endNum.toString()
        }
    }
}