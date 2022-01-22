package org.techtown.gabojago

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import org.techtown.gabojago.databinding.ActivityLoginBinding
import org.techtown.gabojago.databinding.ActivityWheelSelectBinding

class LoginActivity :AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var mOAuthLoginInstance: OAuthLogin
    lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        val clientId =""
        val clientSecret =""
        val clientName =""
        val clientEmail =""

        mContext = this

      // binding.buttonOathLogin.setO

        binding.loginCompBtn.setOnClickListener {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
    }
}