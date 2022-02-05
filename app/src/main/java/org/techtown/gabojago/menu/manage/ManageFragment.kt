package org.techtown.gabojago.menu.manage

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
    var nickName: String = "닉네임"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageBinding.inflate(layoutInflater)

        val manageService = ManageService()
        manageService.setNicknameView(this@ManageFragment)

        val userJwt = getJwt(requireContext(), "userJwt")
        Log.d("USERJWT", userJwt)
        nickName = manageService.getNickname(userJwt).toString()

        binding.manageNicknameModifyBtn.setOnClickListener {
            binding.manageNicknameView.visibility = View.VISIBLE
            binding.manageNicknameEt.setText(binding.manageNicknameTv.text.toString())
        }

        binding.manageNicknameCompBtn.setOnClickListener {
            binding.manageNicknameView.visibility = View.GONE
            binding.manageNicknameTv.text = binding.manageNicknameEt.text.toString()
        }

        return binding.root
    }

    override fun onNicknameSuccess(userNickname: String) {
        binding.manageNicknameTv.text = nickName
    }

    override fun onNicknameFailure(code: Int, message: String) {
        Toast.makeText(
            activity, "Failed.", Toast.LENGTH_SHORT
        ).show()
    }

    override fun onModifyNicknameSuccess(userNickname: String) {
        Toast.makeText(
            activity, "Failed.", Toast.LENGTH_SHORT
        ).show()
    }

    override fun onModifyNicknameFailure(code: Int, message: String) {
        Toast.makeText(
            activity, "Failed.", Toast.LENGTH_SHORT
        ).show()
    }
}