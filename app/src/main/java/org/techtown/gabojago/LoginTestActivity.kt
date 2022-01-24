//package org.techtown.archivebook
//
//import android.R
//import android.content.Context
//import android.content.Intent
//import android.os.AsyncTask
//import android.os.Bundle
//import android.service.autofill.UserData
//import android.telecom.Call
//import android.util.Log
//import android.view.View
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.nhn.android.naverlogin.OAuthLogin
//import com.nhn.android.naverlogin.OAuthLoginHandler
//import com.nhn.android.naverlogin.ui.view.OAuthLoginButton
//import org.json.JSONException
//import org.json.JSONObject
//import org.techtown.gabojago.MainActivity
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//class LoginTestActivity : AppCompatActivity() {
//    var retrofit: Retrofit? = null
//    var userRetrofitInterface: UserRetrofitInterface? = null
//
//    //AVD
//    //private final String SERVER_BASE_URL = "http://10.0.2.2:8080/";
//    //Device
//    //private final String SERVER_BASE_URL = "http://172.30.1.34:8080/";
//    //Server
//    private val SERVER_BASE_URL = "http://13.125.236.123:8080/"
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//        context = this
//
//        // 로그인 인스턴스 초기화
//        val oAuthLoginModule = OAuthLogin.getInstance()
//        oAuthLoginModule.init(
//            this@LoginActivity,
//            resources.getString(R.string.client_id),
//            resources.getString(R.string.client_secret),
//            resources.getString(R.string.client_name)
//        )
//        tv_logout_naver = findViewById<View>(R.id.tv_logout_naver) as TextView
//        btn_login_naver = findViewById<View>(R.id.btn_login_naver) as OAuthLoginButton
//        btn_login_naver!!.setOAuthLoginHandler(oAuthLoginHandler)
//
//        // Retrofit 객체 생성
//        val retrofit: retrofit2.Retrofit = Builder()
//            .baseUrl(SERVER_BASE_URL)
//            .addConverterFactory(NullOnEmptyConverterFactory())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        userRetrofitInterface = retrofit.create(UserRetrofitInterface::class.java)
//        btn_login_naver!!.setOnClickListener {
//            naverLogin()
//
//            //                userRetrofitInterface.getDetail(naverUserId).enqueue(new Callback<UserData>() {
//            //                    @Override
//            //                    public void onResponse(Call<UserData> call, Response<UserData> response) {
//            //                        Log.d("server user detail", "success");
//            //
//            //                        UserData userResult = response.body();
//            //
//            //                        // 회원 조회 결과가 없으면 회원 등록 화면으로 전환
//            //                        if(userResult == null) {
//            //                            Intent intent = new Intent(getApplicationContext(), UserRegister.class);
//            //                            intent.putExtra("account_id", naverUserId);
//            //                            intent.putExtra("name", naverUserName);
//            //                            intent.putExtra("email", naverUserEmail);
//            //                            startActivity(intent);
//            //                            finish();
//            //                        }
//            //                        else {
//            //                            // 메인 화면으로 전환 (로그인)
//            //                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            //                            intent.putExtra("account_id", naverUserId);
//            //                            intent.putExtra("name", naverUserName);
//            //                            intent.putExtra("email", naverUserEmail);
//            //                            startActivity(intent);
//            //                            finish();
//            //                        }
//            //                    }
//            //
//            //                    @Override
//            //                    public void onFailure(Call<UserData> call, Throwable t) {
//            //                        Log.d("server user detail", "fail");
//            //                    }
//            //                });
//        }
//
//        // 로그아웃 텍스트 클릭시 네이버 로그아웃
//        tv_logout_naver!!.setOnClickListener {
//            oAuthLoginModule.logout(context)
//            Toast.makeText(this@LoginActivity, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    private val oAuthLoginHandler: OAuthLoginHandler = object : OAuthLoginHandler() {
//        override fun run(success: Boolean) {
//            if (success) {
//                val accessToken = oAuthLoginModule!!.getAccessToken(context)
//                val refreshToken = oAuthLoginModule!!.getRefreshToken(context)
//                val expiredAt = oAuthLoginModule!!.getExpiresAt(context)
//                val tokenType = oAuthLoginModule!!.getTokenType(context)
//                Toast.makeText(context, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
//                RequestApiTask(context, oAuthLoginModule).execute()
//            } else {
//                val errorCode = oAuthLoginModule!!.getLastErrorCode(context).code
//                val errorDesc = oAuthLoginModule!!.getLastErrorDesc(context)
//                Toast.makeText(context, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    private fun naverLogin() {
//        oAuthLoginModule!!.startOauthLoginActivity(this@LoginActivity, oAuthLoginHandler)
//    }
//
//    class RequestApiTask(private val context: Context?, private val oAuthLoginModule: OAuthLogin?) :
//        AsyncTask<Void?, Void?, String>() {
//        override fun onPreExecute() {}
//        protected override fun doInBackground(vararg voids: Void): String {
//            val url = "https://openapi.naver.com/v1/nid/me"
//            val at = oAuthLoginModule!!.getAccessToken(context)
//            return oAuthLoginModule.requestApi(context, at, url)
//        }
//
//        override fun onPostExecute(content: String) {
//            try {
//                val loginResult = JSONObject(content)
//                if (loginResult.getString("resultcode") == "00") {
//                    val response = loginResult.getJSONObject("response")
//                    naverUserId = response.getString("id")
//                    naverUserName = response.getString("name")
//                    naverUserEmail = response.getString("email")
//                    //Toast.makeText(context, "id : " + naverUserId + "name : " + naverUserName + "email : " + naverUserEmail, Toast.LENGTH_SHORT).show();
//                    val userRetrofitInterface: UserRetrofitInterface
//                    // Retrofit 객체 생성
//                    val retrofit: retrofit2.Retrofit = Builder()
//                        .baseUrl("http://13.125.236.123:8080/")
//                        .addConverterFactory(NullOnEmptyConverterFactory())
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build()
//                    userRetrofitInterface = retrofit.create(UserRetrofitInterface::class.java)
//                    userRetrofitInterface.getDetail(naverUserId)
//                        .enqueue(object : Callback<UserData?>() {
//                            fun onResponse(call: Call<UserData?>?, response: Response<UserData?>) {
//                                Log.d("server user detail", "success")
//                                val userResult: UserData = response.body()
//
//                                // 회원 조회 결과가 없으면 회원 등록 화면으로 전환
//                                if (userResult == null) {
//                                    val intent = Intent(context, UserRegister::class.java)
//                                    intent.putExtra("account_id", naverUserId)
//                                    intent.putExtra("name", naverUserName)
//                                    intent.putExtra("email", naverUserEmail)
//                                    intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
//                                    intent.flags =
//                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                                    context!!.startActivity(intent)
//                                    //finish();
//                                } else {
//                                    // 메인 화면으로 전환 (로그인)
//                                    val intent = Intent(context, MainActivity::class.java)
//                                    intent.putExtra("account_id", naverUserId)
//                                    intent.putExtra("name", naverUserName)
//                                    intent.putExtra("email", naverUserEmail)
//                                    intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
//                                    intent.flags =
//                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                                    context!!.startActivity(intent)
//                                    //finish();
//                                }
//                            }
//
//                            fun onFailure(call: Call<UserData?>?, t: Throwable) {
//                                Log.d("server user detail", "fail $t")
//                            }
//                        })
//                }
//            } catch (e: JSONException) {
//                e.printStackTrace()
//            }
//        }
//    }
//
//    companion object {
//        private var oAuthLoginModule: OAuthLogin? = null
//        private var context: Context? = null
//        private var btn_login_naver: OAuthLoginButton? = null
//        private var tv_logout_naver: TextView? = null
//        var naverUserId: String? = null
//        var naverUserName: String? = null
//        var naverUserEmail: String? = null
//    }
//}