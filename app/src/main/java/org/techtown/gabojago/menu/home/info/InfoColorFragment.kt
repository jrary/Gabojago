package org.techtown.gabojago.menu.home.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.techtown.gabojago.main.MainActivity
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.FragmentHomeInfoBinding
import org.techtown.gabojago.databinding.FragmentInfoColorBinding
import org.techtown.gabojago.menu.home.HomeFragment
import org.techtown.gabojago.menu.home.randomPick.clock.ClockFragment
import org.techtown.gabojago.menu.home.randomPick.color.ColorFragment
import org.techtown.gabojago.menu.home.randomPick.number.NumberFragment
import org.techtown.gabojago.menu.home.randomPick.wheel.WheelFragment

class InfoColorFragment : Fragment() {
    lateinit var binding: FragmentInfoColorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoColorBinding.inflate(inflater, container, false)


        binding.infoColorBackBtn.setOnClickListener {
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