package org.techtown.gabojago.menu.manage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nhn.android.naverlogin.OAuthLogin
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.FragmentManageBinding
import org.techtown.gabojago.main.MyToast
import org.techtown.gabojago.main.getJwt
import org.techtown.gabojago.main.setJwt
import org.techtown.gabojago.start.splash.SplashActivity

class ManageFragment : Fragment(), NicknameView, NewNicknameView, LogoutView, WithdrawalView {
    lateinit var binding: FragmentManageBinding
    private lateinit var oAuthLogin: OAuthLogin
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageBinding.inflate(layoutInflater)

        oAuthLogin = OAuthLogin.getInstance()
        oAuthLogin.init(requireContext(),"6dp8qdfztnBLiguo_gLx", "77OipGRnx9", "Gabojago")

        val userJwt = getJwt(requireContext(), "userJwt")
        Log.d("USERJWT", userJwt)

        val manageService = ManageService()
        manageService.setNicknameView(this@ManageFragment)
        manageService.setNewNicknameView(this@ManageFragment)
        manageService.setLogoutView(this@ManageFragment)
        manageService.setWithdrawalView(this@ManageFragment)

        manageService.getNickname(userJwt)

        binding.manageNicknameModifyBtn.setOnClickListener {
            binding.manageNicknameView.visibility = View.VISIBLE
            binding.manageNicknameEt.setText(binding.manageNicknameTv.text.toString())
        }

        binding.manageNicknameCompBtn.setOnClickListener {
            val modifyNickname = binding.manageNicknameEt.text.toString()
            if(modifyNickname.length >= 25){
                MyToast.createToast(
                    requireContext(), "닉네임을 25자 이하로 설정해 주세요"
                )?.show()
            }
            else{
                binding.manageNicknameView.visibility = View.GONE
                manageService.modifyNickname(userJwt, modifyNickname)
            }
        }

        binding.manageChatTv.setOnClickListener {
            val feedback = Intent(Intent.ACTION_VIEW, Uri.parse("https://forms.gle/bMn73YNkNLKqvWHq9"))
            startActivity(feedback)
        }

        binding.manageLogoutBtn.setOnClickListener {
            manageService.logout(userJwt)
        }

        binding.manageExitTv.setOnClickListener {
            manageService.withdrawal(userJwt)
        }

        return binding.root
    }

    override fun onNicknameLoading() {
        binding.manageLoadingView.visibility = View.VISIBLE
        for(i in 0..5){
            Handler().postDelayed({
                binding.manageLoadingIv.setImageResource(R.drawable.loading_02)
            }, (500 + 1500 * i).toLong())
            Handler().postDelayed({
                binding.manageLoadingIv.setImageResource(R.drawable.loading_03)
            }, (1000 + 1500 * i).toLong())
            Handler().postDelayed({
                binding.manageLoadingIv.setImageResource(R.drawable.loading_01)
            }, (1500 + 1500 * i).toLong())
        }
    }

    override fun onNicknameSuccess(userNickname: String) {
        binding.manageLoadingView.visibility = View.GONE
        binding.manageNicknameTv.text = userNickname
    }

    override fun onNicknameFailure(code: Int, message: String) {
        binding.manageLoadingView.visibility = View.GONE
        MyToast.createToast(
            requireContext(), message
        )?.show()
    }

    override fun onModifyNicknameLoading() {
        binding.manageLoadingView.visibility = View.VISIBLE
        for(i in 0..5){
            Handler().postDelayed({
                binding.manageLoadingIv.setImageResource(R.drawable.loading_02)
            }, (500 + 1500 * i).toLong())
            Handler().postDelayed({
                binding.manageLoadingIv.setImageResource(R.drawable.loading_03)
            }, (1000 + 1500 * i).toLong())
            Handler().postDelayed({
                binding.manageLoadingIv.setImageResource(R.drawable.loading_01)
            }, (1500 + 1500 * i).toLong())
        }
    }

    override fun onModifyNicknameSuccess(newNickName: String) {
        binding.manageLoadingView.visibility = View.GONE
        binding.manageNicknameTv.text = newNickName
    }

    override fun onModifyNicknameFailure(code: Int, message: String) {
        binding.manageLoadingView.visibility = View.GONE
        MyToast.createToast(
            requireContext(), message
        )?.show()
    }

    override fun onLogoutLoading() {
        binding.manageLoadingView.visibility = View.VISIBLE
        for(i in 0..5){
            Handler().postDelayed({
                binding.manageLoadingIv.setImageResource(R.drawable.loading_02)
            }, (500 + 1500 * i).toLong())
            Handler().postDelayed({
                binding.manageLoadingIv.setImageResource(R.drawable.loading_03)
            }, (1000 + 1500 * i).toLong())
            Handler().postDelayed({
                binding.manageLoadingIv.setImageResource(R.drawable.loading_01)
            }, (1500 + 1500 * i).toLong())
        }
    }

    override fun onLogoutSuccess() {
        oAuthLogin.logout(requireContext())
        binding.manageLoadingView.visibility = View.GONE
        setJwt(requireContext(), "userJwt", "")
        MyToast.createToast(
            requireContext(), "로그아웃 되었습니다."
        )?.show()
        startActivity(Intent(requireContext(), SplashActivity::class.java))
    }

    override fun onLogoutFailure(code: Int, message: String) {
        binding.manageLoadingView.visibility = View.GONE
        MyToast.createToast(
            requireContext(), message
        )?.show()
    }

    override fun onWithdrawalLoading() {

        binding.manageLoadingView.visibility = View.VISIBLE
        for(i in 0..5){
            Handler().postDelayed({
                binding.manageLoadingIv.setImageResource(R.drawable.loading_02)
            }, (500 + 1500 * i).toLong())
            Handler().postDelayed({
                binding.manageLoadingIv.setImageResource(R.drawable.loading_03)
            }, (1000 + 1500 * i).toLong())
            Handler().postDelayed({
                binding.manageLoadingIv.setImageResource(R.drawable.loading_01)
            }, (1500 + 1500 * i).toLong())
        }
    }

    override fun onWithdrawalSuccess() {
        binding.manageLoadingView.visibility = View.GONE
        setJwt(requireContext(), "userJwt", "")
        MyToast.createToast(
            requireContext(), "회원 탈퇴가 완료되었습니다."
        )?.show()
        startActivity(Intent(requireContext(), SplashActivity::class.java))
    }

    override fun onWithdrawalFailure(code: Int, message: String) {
        binding.manageLoadingView.visibility = View.GONE
        MyToast.createToast(
            requireContext(), message
        )?.show()
    }
}