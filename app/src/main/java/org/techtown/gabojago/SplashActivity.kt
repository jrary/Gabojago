package org.techtown.gabojago

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import org.techtown.gabojago.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animation01 = AnimationUtils.loadAnimation(this, R.anim.anim_toleft_splash_01)
        binding.splashLogoIv.startAnimation(animation01)
        binding.splashLogoBarIv.startAnimation(animation01)

        Handler().postDelayed({
            val animationAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha_splash)
            binding.splashLogoBarIv.startAnimation(animationAlpha)
        }, 500)

        Handler().postDelayed({
            binding.splashLogoBarIv.visibility = View.GONE
//            binding.splashLogoIv.visibility = View.GONE
//            binding.splashLogoActionIv.visibility = View.VISIBLE

//            val rot = AnimationUtils.loadAnimation(this, R.anim.anim_rotate_splash)
//            val animation02 = AnimationUtils.loadAnimation(this, R.anim.anim_toleft_splash_02)
//            binding.splashLogoIv.startAnimation(rot)
//            binding.splashLogoIv.startAnimation(animation02)

            binding.splashLogoIv.animate()
                    .rotation(0.0f)
                    .translationX(0f * 150)
                    .withStartAction{}
                    .withEndAction{
                binding.splashLogoIv.rotation = 15.0f;
                binding.splashLogoIv.translationX = -400f
            }.start()
        }, 1000)

        Handler(Looper.getMainLooper()).postDelayed({
            binding.splashLogoIv.visibility = View.GONE
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }, 1500)
    }
}