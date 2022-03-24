package org.techtown.gabojago.start.login

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import kotlinx.android.synthetic.main.activity_login.*
import org.techtown.gabojago.*
import org.techtown.gabojago.databinding.ActivityLoginBinding
import org.techtown.gabojago.main.*

class LoginActivity :AppCompatActivity(), LoginView {

    lateinit var binding: ActivityLoginBinding
    lateinit var mOAuthLoginInstance: OAuthLogin
    lateinit var mContext: Context
    var remainLogin: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x
        val height = size.y

        binding.loginBackgroundView.layoutParams.width = width
        binding.loginBackgroundView.layoutParams.height = height*2/3

        binding.loginNaverBtn.layoutParams.width = width*2/3
        binding.loginNaverBtn.layoutParams.height = binding.loginNaverBtn.layoutParams.width/4

        Handler().postDelayed({
            binding.loginBackgroundView.visibility = View.VISIBLE
            val animDrop = AnimationUtils.loadAnimation(this, R.anim.anim_login_down)
            binding.loginBackgroundView.startAnimation(animDrop)
        }, 100)

        binding.loginCb.setOnCheckedChangeListener { button, isChecked ->
            remainLogin = isChecked
        }

        mContext = this

        mOAuthLoginInstance = OAuthLogin.getInstance()
        mOAuthLoginInstance.init(mContext, "6dp8qdfztnBLiguo_gLx", "77OipGRnx9", "Gabojago")
        binding.loginNaverBtn.setOAuthLoginHandler(mOAuthLoginHandler)
    }

    private val mOAuthLoginHandler: OAuthLoginHandler = object: OAuthLoginHandler(){
        override fun run(success: Boolean) {
            if (success) {
                val accessToken: String = mOAuthLoginInstance.getAccessToken(baseContext)
                val refreshToken: String = mOAuthLoginInstance.getRefreshToken(baseContext)
                val expiresAt: Long = mOAuthLoginInstance.getExpiresAt(baseContext)
                val tokenType: String = mOAuthLoginInstance.getTokenType(baseContext)

                Log.d("ACCESSTOKEN", accessToken)
                setJwt(this@LoginActivity, "accessToken", accessToken)

                val authService = AuthService()
                authService.setLoginView(this@LoginActivity)

                authService.login(accessToken)

            } else {
                val errorCode: String = mOAuthLoginInstance.getLastErrorCode(mContext).code
                val errorDesc = mOAuthLoginInstance.getLastErrorDesc(mContext)
                MyToast.createToast(
                    baseContext, "errorCode:" + errorCode
                            + ", errorDesc:" + errorDesc, 50, true
                ).show()
            }
        }
    }

    override fun onLoginSuccess(userJwt: String) {
        setJwt(this, "userJwt", userJwt)
        setBooleanJwt(this, "remainLogin", remainLogin)
        Log.d("USERJWT", userJwt)
        var intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onLoginFailure(code: Int, message: String) {
        when(code){
            400, 5014 -> {
                MyToast.createToast(
                    baseContext, message, 90, true
                ).show()
            }
            5013, 5015 -> {
                MyToast.createToast(
                    baseContext, "로그인 시스템에 문제가 발생하였습니다.", 50, true
                ).show()
                Log.d("LOGINERROR", message)
            }
            else -> {

                MyToast.createToast(
                    baseContext, message, 50, true
                ).show()
            }
        }
    }
}