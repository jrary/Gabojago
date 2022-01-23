package org.techtown.gabojago.randomPick.color

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.FragmentColorBinding

class ColorFragment : Fragment() {
    lateinit var binding: FragmentColorBinding
    var setStart: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = FragmentColorBinding.inflate(layoutInflater)

        if(setStart){
            binding.colorCard05View.visibility = View.VISIBLE
            setStart = false
        }
        binding.colorCard01View.setImageResource(R.drawable.vending_card_selected)
        binding.colorBtn.setOnClickListener {
            cardColorAnimation()
        }

        return binding.root
    }
    private fun cardColorAnimation(){
        Handler().postDelayed({
            binding.colorCard01View.setImageResource(R.drawable.vending_card)
            binding.colorCard09View.setImageResource(R.drawable.vending_card_selected)
        }, 200)
        Handler().postDelayed({
            binding.colorCard09View.setImageResource(R.drawable.vending_card)
            binding.colorCard07View.setImageResource(R.drawable.vending_card_selected)
        }, 400)
        Handler().postDelayed({
            binding.colorCard07View.setImageResource(R.drawable.vending_card)
            binding.colorCard02View.setImageResource(R.drawable.vending_card_selected)
        }, 600)
        Handler().postDelayed({
            binding.colorCard02View.setImageResource(R.drawable.vending_card)
            binding.colorCard06View.setImageResource(R.drawable.vending_card_selected)
        }, 800)
        Handler().postDelayed({
            binding.colorCard06View.setImageResource(R.drawable.vending_card)
            binding.colorCard05View.setImageResource(R.drawable.vending_card_selected)
        }, 1000)
        Handler().postDelayed({
            binding.colorCard05View.visibility = View.GONE
        }, 1200)
        Handler().postDelayed({
            startActivity(Intent(activity, ColorResultActivity::class.java))
        }, 1400)
    }

    override fun onResume() {
        super.onResume()
        binding.colorCard01View.setImageResource(R.drawable.vending_card_selected)
        binding.colorCard05View.visibility = View.VISIBLE
        binding.colorCard05View.setImageResource(R.drawable.vending_card)
    }
}