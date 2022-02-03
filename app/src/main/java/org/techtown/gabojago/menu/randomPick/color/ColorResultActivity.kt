package org.techtown.gabojago.menu.randomPick.color

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.Toast
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

        var randomColorImageArr = arrayOf(
            R.drawable.color_red,
            R.drawable.color_orange,
            R.drawable.color_yellow,
            R.drawable.color_green,
            R.drawable.color_blue,
            R.drawable.color_black,
            R.drawable.color_brown,
            R.drawable.color_white,
            R.drawable.color_magenta,
        )

        binding.colorResultQuestionBtn.setOnClickListener{
            binding.colorResultQuestionView.visibility = View.GONE
            binding.colorResultView.visibility = View.VISIBLE
            binding.colorResultView.startAnimation(animAlphaStart)

            var randRes = setRandom()
            binding.colorResultColorTv.text = randomColor[randRes]
            binding.colorResultColorIv.setImageResource(randomColorImageArr[randRes])
        }

        binding.colorResultRetryBtn.setOnClickListener {
            finish()
        }
        binding.colorResultSaveBtn.setOnClickListener {
            Toast.makeText(
                this, "뽑기 결과가 저장됐어!", Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setRandom(): Int{
        val random = Random()
        return random.nextInt(9)
    }
}