package org.techtown.gabojago

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.techtown.gabojago.databinding.FragmentHomeBinding
import org.techtown.gabojago.randomPick.HomeMenuFragment

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        var orangeText = SpannableStringBuilder("랜덤에 내 발길을 맡겨볼까?\n새로운 장소를 발견하고 탐험해봐!")
        Log.d("ERROR", binding.homeInfoTv.text.toString())
        orangeText.setSpan(ForegroundColorSpan(Color.rgb(255,103,69)), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.homeInfoTv.text = orangeText

        binding.homeStartIv.setOnClickListener{
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeMenuFragment())
                .commitAllowingStateLoss()
        }

        return binding.root
    }
}