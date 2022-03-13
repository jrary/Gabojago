package org.techtown.gabojago.menu.manage.system

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.nhn.android.naverlogin.OAuthLogin
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.FragmentManageBinding
import org.techtown.gabojago.main.MainActivity
import org.techtown.gabojago.main.MyToast
import org.techtown.gabojago.main.getJwt
import org.techtown.gabojago.main.setJwt
import org.techtown.gabojago.menu.home.contents.HomeFragment
import org.techtown.gabojago.menu.manage.*
import org.techtown.gabojago.menu.manage.auth.LogoutDialog
import org.techtown.gabojago.menu.manage.auth.WithdrawalDialog
import org.techtown.gabojago.start.splash.SplashActivity

class ManageFragment : Fragment(), NicknameView, NewNicknameView {
    lateinit var binding: FragmentManageBinding
    private lateinit var callback: OnBackPressedCallback
    private lateinit var oAuthLogin: OAuthLogin

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val mainContext = context as MainActivity
                mainContext.binding.mainBnv.selectedItemId = R.id.homeFragment
                Log.e("back","backpress")
                mainContext.supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, HomeFragment())
                    .commitAllowingStateLoss()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }



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

        manageService.getNickname(userJwt)

        binding.manageNicknameModifyBtn.setOnClickListener {
            binding.manageNicknameView.visibility = View.VISIBLE
            binding.manageNicknameEt.setText(binding.manageNicknameTv.text.toString())
        }

        binding.manageNicknameCompBtn.setOnClickListener {
            val modifyNickname = binding.manageNicknameEt.text.toString()
            if(modifyNickname.length >= 25){
                MyToast.createToast(
                    requireContext(), "닉네임을 25자 이하로 설정해 주세요", 90, true
                ).show()
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
            LogoutDialog().show((context as MainActivity).supportFragmentManager,"dialog")
        }

        binding.manageExitTv.setOnClickListener {
            WithdrawalDialog().show((context as MainActivity).supportFragmentManager,"dialog")
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
            requireContext(), message, 90, true
        ).show()
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
            requireContext(), message, 90, true
        ).show()
    }
}