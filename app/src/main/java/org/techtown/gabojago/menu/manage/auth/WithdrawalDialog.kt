package org.techtown.gabojago.menu.manage.auth

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.nhn.android.naverlogin.OAuthLogin
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.DialogWithdrawalBinding
import org.techtown.gabojago.main.MyToast
import org.techtown.gabojago.main.getJwt
import org.techtown.gabojago.main.setJwt
import org.techtown.gabojago.menu.manage.ManageService
import org.techtown.gabojago.start.splash.SplashActivity


class WithdrawalDialog : DialogFragment(), WithdrawalView {

    private lateinit var oAuthLogin: OAuthLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //false로 설정해 주면 화면밖 혹은 뒤로가기 버튼시 다이얼로그라 dismiss 되지 않는다.
        isCancelable = true
    }

    private lateinit var binding: DialogWithdrawalBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogWithdrawalBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        oAuthLogin = OAuthLogin.getInstance()
        oAuthLogin.init(requireContext(),"6dp8qdfztnBLiguo_gLx", "77OipGRnx9", "Gabojago")

        val manageService = ManageService()
        manageService.setWithdrawalView(this@WithdrawalDialog)

        binding.dialogYesBtn.setOnClickListener {
            val userJwt = getJwt(requireContext(), "userJwt")
            manageService.withdrawal(userJwt)
        }

        binding.dialogNoBtn.setOnClickListener {
            dismiss()
        }
        return binding.root
    }

    override fun onWithdrawalSuccess() {
       // oAuthLogin.logout(requireContext())
        setJwt(requireContext(), "userJwt", "")
        MyToast.createToast(
            requireContext(), "회원 탈퇴가 완료되었습니다."
        )?.show()
        startActivity(Intent(requireContext(), SplashActivity::class.java))
    }

    override fun onWithdrawalFailure(code: Int, message: String) {
        MyToast.createToast(
            requireContext(), message
        )?.show()
    }
}


