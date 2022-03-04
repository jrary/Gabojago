package org.techtown.gabojago.menu.home.contents

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import androidx.fragment.app.Fragment
import org.techtown.gabojago.main.MainActivity
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.FragmentHomeBinding
import org.techtown.gabojago.menu.home.HomeMenuFragment
import org.techtown.gabojago.menu.home.info.HomeInfoFragment
import java.util.concurrent.TimeUnit


class HomeFragment : Fragment(){
    lateinit var binding: FragmentHomeBinding
    private lateinit var loopAnim: LoopAnimation

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        //Set the text color
        var orangeText = SpannableStringBuilder("랜덤에 내 발길을 맡겨볼까?\n새로운 장소를 발견하고 탐험해봐!")
        orangeText.setSpan(ForegroundColorSpan(Color.rgb(255,103,69)), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.homeInfoTv.text = orangeText

        binding.homeStartIv.setOnClickListener{
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeMenuFragment().apply {
                    arguments = Bundle().apply {
                    }
                })
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }

        binding.homeDice01Iv.setOnClickListener{
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeInfoFragment().apply {
                    arguments = Bundle().apply {
                    }
                })
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }

        rotateSet()
        loopAnim = LoopAnimation()
        loopAnim.start()

        return binding.root
    }

    private fun rotateSet(){
        val rotate = RotateAnimation(0f, 21.92f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotate.fillAfter = true
        binding.homeStartIv.startAnimation(rotate)

        val rotate02 = RotateAnimation(0f, -21.02f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotate02.fillAfter = true
        binding.homeDice01Iv.startAnimation(rotate02)

        val rotate03 = RotateAnimation(0f, -45f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotate03.fillAfter = true
        binding.homeDice02Iv.startAnimation(rotate03)
    }

    inner class LoopAnimation : Thread() {

        @SuppressLint("SetTextI18n")
        override fun run() {
            try {
                var i = 0
                val startAnimation01 = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_dice_start_01)
                while (true) {
                    binding.homeStartIv.startAnimation(startAnimation01)
                }
            } catch (e: InterruptedException) {
                Log.d("SONG", "쓰레드가 죽었습니다. ${e.message}")
            }
        }
    }
}