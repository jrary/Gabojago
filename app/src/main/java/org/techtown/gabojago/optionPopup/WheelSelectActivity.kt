package org.techtown.gabojago.optionPopup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.techtown.gabojago.databinding.ActivityWheelOptionBinding
import org.techtown.gabojago.databinding.ActivityWheelSelectBinding

class WheelSelectActivity : AppCompatActivity() {

    lateinit var binding: ActivityWheelSelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWheelSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}