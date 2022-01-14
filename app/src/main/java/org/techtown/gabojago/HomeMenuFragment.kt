package org.techtown.gabojago

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.techtown.gabojago.databinding.FragmentHomeMenuBinding
import org.techtown.gabojago.randomPick.ClockFragment
import org.techtown.gabojago.randomPick.ColorFragment
import org.techtown.gabojago.randomPick.NumberFragment
import org.techtown.gabojago.randomPick.WheelFragment

class HomeMenuFragment : Fragment() {
    lateinit var binding: FragmentHomeMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeMenuBinding.inflate(inflater, container, false)


        binding.homeMenuWheelView.setOnClickListener{
            (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, WheelFragment())
                    .commitAllowingStateLoss()
        }
        binding.homeMenuClockView.setOnClickListener{
            (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, ClockFragment())
                    .commitAllowingStateLoss()
        }
        binding.homeMenuNumberView.setOnClickListener{
            (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, NumberFragment())
                    .commitAllowingStateLoss()
        }
        binding.homeMenuColorView.setOnClickListener{
            (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, ColorFragment())
                    .commitAllowingStateLoss()
        }

        return binding.root
    }
}