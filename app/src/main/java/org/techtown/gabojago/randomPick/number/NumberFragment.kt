package org.techtown.gabojago.randomPick

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.techtown.gabojago.databinding.FragmentNumberBinding

class NumberFragment : Fragment() {
    lateinit var binding: FragmentNumberBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = FragmentNumberBinding.inflate(layoutInflater)
        return binding.root
    }
}