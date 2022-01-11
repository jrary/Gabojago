package org.techtown.gabojago

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.techtown.gabojago.databinding.FragmentHomeMenuBinding

class HomeMenuFragment : Fragment() {
    lateinit var binding: FragmentHomeMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeMenuBinding.inflate(inflater, container, false)

        binding.homeStartIv.setOnClickListener{
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeMenuFragment())
                .commitAllowingStateLoss()
        }

        binding.homeStartIv.setOnClickListener{
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeMenuFragment())
                .commitAllowingStateLoss()
        }

        binding.homeStartIv.setOnClickListener{
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeMenuFragment())
                .commitAllowingStateLoss()
        }

        binding.homeStartIv.setOnClickListener{
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeMenuFragment())
                .commitAllowingStateLoss()
        }

        return binding.root
    }
}