package org.techtown.gabojago.menu.manage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.techtown.gabojago.databinding.FragmentManageBinding
import org.techtown.gabojago.main.getJwt

class ManageFragment : Fragment(), NicknameView, NewNicknameView {
    lateinit var binding: FragmentManageBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageBinding.inflate(layoutInflater)

        val manageService = ManageService()
        manageService.setNicknameView(this@ManageFragment)
        manageService.setNewNicknameView(this@ManageFragment)

        val userJwt = getJwt(requireContext(), "userJwt")
        Log.d("USERJWT", userJwt)
        manageService.getNickname(userJwt)

        binding.manageNicknameModifyBtn.setOnClickListener {
            binding.manageNicknameView.visibility = View.VISIBLE
            binding.manageNicknameEt.setText(binding.manageNicknameTv.text.toString())
        }

        binding.manageNicknameCompBtn.setOnClickListener {
            val modifyNickname = binding.manageNicknameEt.text.toString()
            if(modifyNickname.length >= 25){
                Toast.makeText(
                    activity, "닉네임을 25자 이하로 설정해 주세요", Toast.LENGTH_SHORT
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

        return binding.root
    }

    override fun onNicknameSuccess(userNickname: String) {
        binding.manageNicknameTv.text = userNickname
    }

    override fun onNicknameFailure(code: Int, message: String) {
        Toast.makeText(
            activity, message, Toast.LENGTH_SHORT
        ).show()
    }

    override fun onModifyNicknameSuccess(newNickName: String) {
        binding.manageNicknameTv.text = newNickName
    }

    override fun onModifyNicknameFailure(code: Int, message: String) {
        Toast.makeText(
            activity, message, Toast.LENGTH_SHORT
        ).show()
    }
}