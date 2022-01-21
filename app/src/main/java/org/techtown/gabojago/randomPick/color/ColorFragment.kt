package org.techtown.gabojago.randomPick

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        return binding.root
    }
}