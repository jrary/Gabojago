package org.techtown.gabojago.menu.home.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.techtown.gabojago.main.MainActivity
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.FragmentHomeInfoBinding
import org.techtown.gabojago.databinding.FragmentInfoNumberBinding
import org.techtown.gabojago.databinding.FragmentNumberBinding
import org.techtown.gabojago.menu.home.HomeFragment
import org.techtown.gabojago.menu.home.randomPick.clock.ClockFragment
import org.techtown.gabojago.menu.home.randomPick.color.ColorFragment
import org.techtown.gabojago.menu.home.randomPick.number.NumberFragment
import org.techtown.gabojago.menu.home.randomPick.wheel.WheelFragment

class InfoNumberFragment : Fragment() {
    lateinit var binding: FragmentInfoNumberBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoNumberBinding.inflate(inflater, container, false)


        binding.infoNumberBackBtn.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeInfoFragment().apply {
                    arguments = Bundle().apply {
                    }
                })
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }

        return binding.root
    }
}