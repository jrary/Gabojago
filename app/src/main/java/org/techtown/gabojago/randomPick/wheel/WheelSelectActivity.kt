package org.techtown.gabojago.randomPick.wheel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatActivity
import org.techtown.gabojago.databinding.ActivitySelectBinding

class WheelSelectActivity : AppCompatActivity() {

    lateinit var binding: ActivitySelectBinding
    var res: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        initNumberPicker()

        binding.selectCompBtn.setOnClickListener{
            val intent = Intent()
            intent.putExtra("clock", res.toString())
            setResult(100, intent)
            finish()
        }

        binding.selectNumberPicker.setOnValueChangedListener{
            picker, i1, i2 ->
            res = i2
        }
    }
    private fun initNumberPicker(){

        binding.selectNumberPicker.wrapSelectorWheel = false
        binding.selectNumberPicker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        val data1: Array<String> = Array(11){
            i -> i.toString()
        }
        binding.selectNumberPicker.minValue = 0
        binding.selectNumberPicker.maxValue = data1.size-1
        binding.selectNumberPicker.wrapSelectorWheel = false
        binding.selectNumberPicker.displayedValues = data1
    }
}