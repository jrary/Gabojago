package org.techtown.gabojago.randomPick

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import org.techtown.gabojago.databinding.ActivityWheelBinding
import org.techtown.gabojago.databinding.FragmentHomeBinding

class NumberActivity : AppCompatActivity() {
    lateinit var binding: ActivityWheelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWheelBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}