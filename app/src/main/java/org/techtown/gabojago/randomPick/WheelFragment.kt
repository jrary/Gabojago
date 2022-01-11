package org.techtown.gabojago.randomPick

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.techtown.gabojago.databinding.FragmentWheelBinding


class WheelFragment : Fragment() {
    lateinit var binding: FragmentWheelBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWheelBinding.inflate(layoutInflater)
        return binding.root
    }
}