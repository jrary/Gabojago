package org.techtown.gabojago.randomPick.clock

import android.os.Bundle
import android.view.WindowManager
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatActivity
import org.techtown.gabojago.databinding.ActivityWheelSelectBinding

class ClockSelectActivity : AppCompatActivity() {

    lateinit var binding: ActivityWheelSelectBinding
    var pNum: String = ""

    interface MyTimeClickListener {
        fun onTimeClick()
    }

    private lateinit var mTimeClickListener: MyTimeClickListener

    fun setMyTimeClickListener(itemClickListener: MyTimeClickListener) {
        mTimeClickListener = itemClickListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWheelSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        initNumberPicker()
        binding.wheelSelectNumberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            pNum = newVal.toString()
        }

        binding.wheelSelectCompBtn.setOnClickListener{
            mTimeClickListener.onTimeClick()
            finish()

        }
    }
    private fun initNumberPicker(){

        val data1: Array<String> = Array(12){
            i -> (i+1).toString()
        }
        binding.wheelSelectNumberPicker.minValue = 1
        binding.wheelSelectNumberPicker.maxValue = 12
        binding.wheelSelectNumberPicker.wrapSelectorWheel = false
        binding.wheelSelectNumberPicker.displayedValues = data1
        binding.wheelSelectNumberPicker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
    }
}