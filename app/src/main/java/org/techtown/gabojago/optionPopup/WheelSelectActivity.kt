package org.techtown.gabojago.optionPopup

import android.os.Bundle
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.techtown.gabojago.databinding.ActivityWheelOptionBinding
import org.techtown.gabojago.databinding.ActivityWheelSelectBinding

class WheelSelectActivity : AppCompatActivity() {

    lateinit var binding: ActivityWheelSelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWheelSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNumberPicker()

        binding.wheelSelectNumberPicker.setOnValueChangedListener{
            picker, i1, i2 ->
        }

    }
    private fun initNumberPicker(){

        numberPickerDesign()

        binding.wheelSelectNumberPicker.wrapSelectorWheel = false
        binding.wheelSelectNumberPicker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        val data1: Array<String> = Array(50){
            i -> i.toString()
        }
        binding.wheelSelectNumberPicker.minValue = 0
        binding.wheelSelectNumberPicker.maxValue = data1.size-1
        binding.wheelSelectNumberPicker.wrapSelectorWheel = false
        binding.wheelSelectNumberPicker.displayedValues = data1
    }

    private fun numberPickerDesign(){



    }
}