package org.techtown.gabojago.randomPick.clock

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.techtown.gabojago.databinding.FragmentClockBinding


class ClockFragment : Fragment() {
    lateinit var binding: FragmentClockBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = FragmentClockBinding.inflate(layoutInflater)

        binding.clockOptionBtn.setOnClickListener {
            startActivity(Intent(activity, ClockOptionActivity::class.java))
        }

        return binding.root
    }
}