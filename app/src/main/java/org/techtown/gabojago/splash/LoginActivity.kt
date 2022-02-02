package org.techtown.gabojago.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import kotlinx.android.synthetic.main.activity_login.*
import org.techtown.gabojago.AuthRetrofitInterface
import org.techtown.gabojago.MainActivity
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.ActivityLoginBinding
import org.techtown.gabojago.getRetrofit

class LoginActivity :AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var mOAuthLoginInstance: OAuthLogin
    lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        val anim = AnimationUtils.loadAnimation(this, R.anim.anim_login_down)
        binding.loginBackgroundIv.startAnimation(anim)

        mContext = this

        mOAuthLoginInstance = OAuthLogin.getInstance()
        mOAuthLoginInstance.init(mContext, "6dp8qdfztnBLiguo_gLx", "77OipGRnx9", "Gabojago")
        binding.loginNaverBtn.setOAuthLoginHandler(mOAuthLoginHandler)

        binding.loginCompBtn.setOnClickListener {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
    }

    private val mOAuthLoginHandler: OAuthLoginHandler = object: OAuthLoginHandler(){
        override fun run(success: Boolean) {
            if (success) {
                Toast.makeText(baseContext,"Login Success", Toast.LENGTH_SHORT).show()
                val accessToken: String = mOAuthLoginInstance.getAccessToken(baseContext)
                val refreshToken: String = mOAuthLoginInstance.getRefreshToken(baseContext)
                val expiresAt: Long = mOAuthLoginInstance.getExpiresAt(baseContext)
                val tokenType: String = mOAuthLoginInstance.getTokenType(baseContext)
                Log.d("Token", accessToken)
                Toast.makeText(baseContext,"Login Success", Toast.LENGTH_SHORT).show()
                val test: AuthRetrofitInterface = getRetrofit().create(AuthRetrofitInterface::class.java)
                var intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            } else {
                val errorCode: String = mOAuthLoginInstance.getLastErrorCode(mContext).code
                val errorDesc = mOAuthLoginInstance.getLastErrorDesc(mContext)
                Toast.makeText(
                    baseContext, "errorCode:" + errorCode
                            + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}