package org.techtown.gabojago.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import org.techtown.gabojago.LoginActivity
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
            val animation01 = AnimationUtils.loadAnimation(this, R.anim.anim_toleft_splash_01)
            binding.splashView.startAnimation(animation01)
        }, 100)

        Handler().postDelayed({
            val animationAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha_splash)
            binding.splashLogoBarIv.startAnimation(animationAlpha)
        }, 400)

        Handler().postDelayed({
            val animationAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_toleft_splash_02)
            binding.splashLogoIv.startAnimation(animationAlpha)
        }, 1200)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 1500)
    }
}