package org.techtown.gabojago.randomPick

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.techtown.gabojago.databinding.FragmentWheelBinding


class WheelFragment : Fragment() {
    lateinit var binding: FragmentWheelBinding

    private val activity = context as Activity
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWheelBinding.inflate(layoutInflater)

        binding.wheelOptionBtn.setOnClickListener {
            binding.wheelOptionView.visibility = View.VISIBLE
        }

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
}