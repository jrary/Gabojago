package org.techtown.gabojago.randomPick.color

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.techtown.gabojago.databinding.ActivityColorResultBinding
import org.techtown.gabojago.databinding.ActivityWheelOptionBinding
import org.techtown.gabojago.optionPopup.WheelOptionData
import org.techtown.gabojago.optionPopup.WheelSelectActivity
import java.util.ArrayList

class ColorResultActivity : AppCompatActivity() {

    lateinit var binding: ActivityColorResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityColorResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}