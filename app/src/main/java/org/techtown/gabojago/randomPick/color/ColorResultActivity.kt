package org.techtown.gabojago.randomPick.color

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.ActivityColorResultBinding
import java.util.*

class ColorResultActivity : AppCompatActivity() {

    lateinit var binding: ActivityColorResultBinding

    var randomColor = arrayOf(
        "빨간색 계열!",
        "주황색 계열!",
        "노란색 계열!",
        "초록색 계열!",
        "파란색 계열!",
        "검은색 계열!",
        "갈색 계열!",
        "흰색 계열!",
        "마젠타 계열!",
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityColorResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val animAlphaStart = AnimationUtils.loadAnimation(this, R.anim.anim_alpha_start)
        binding.colorResultQuestionBtn.setOnClickListener{
            binding.colorResultQuestionView.visibility = View.GONE
            binding.colorResultView.visibility = View.VISIBLE
            binding.colorResultView.startAnimation(animAlphaStart)
            binding.colorResultColorTv.text = randomColor[setRandom()]
        }

        binding.colorResultRetryBtn.setOnClickListener {
            finish()
        }
    }

    private fun setRandom(): Int{
        val random = Random()
        return random.nextInt(9)
    }
}