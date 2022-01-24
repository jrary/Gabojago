package org.techtown.gabojago.randomPick.clock

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.techtown.gabojago.databinding.ActivityWheelOptionBinding
import org.techtown.gabojago.databinding.ActivityWheelSelectBinding

class ClockSelectActivity : AppCompatActivity() {

    lateinit var binding: ActivityWheelSelectBinding
    var pickedNum: Int = 12

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWheelSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        initNumberPicker()

        binding.wheelSelectCompBtn.setOnClickListener{
            finish()
            pickedNum = binding.wheelSelectNumberPicker.value
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