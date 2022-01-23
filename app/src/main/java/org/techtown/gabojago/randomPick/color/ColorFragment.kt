package org.techtown.gabojago.randomPick.color

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment
import org.techtown.gabojago.databinding.FragmentColorBinding

class ColorFragment : Fragment() {
    lateinit var binding: FragmentColorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = FragmentColorBinding.inflate(layoutInflater)

        binding.colorBtn.setOnClickListener {
            startActivity(Intent(activity, ColorResultActivity::class.java))
        }

        return binding.root
    }
}