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
            binding.manageNicknameView.visibility = View.GONE
            val modifyNickname = binding.manageNicknameEt.text.toString()
            if(modifyNickname.length >= 25){
                Toast.makeText(
                    activity, "length", Toast.LENGTH_SHORT
                ).show()
            }
            else{
                manageService.modifyNickname(userJwt, modifyNickname)
            }
        }

        return binding.root
    }

    override fun onNicknameSuccess(userNickname: String) {
        binding.manageNicknameTv.text = userNickname
    }

    override fun onNicknameFailure(code: Int, message: String) {
        when(code){
            400, 2013 -> {
                Toast.makeText(
                    activity, message, Toast.LENGTH_SHORT
                ).show()
            }
            3000 -> {
                Log.d("Token Error", message)
            }
            else -> {
                Log.d("GETNICK_failure_noCode", code.toString())
            }
        }
    }

    override fun onModifyNicknameSuccess(newNickName: String) {
        binding.manageNicknameTv.text = newNickName
    }

    override fun onModifyNicknameFailure(code: Int, message: String) {
        when(code){
            400, 2013, 2017, 7002 -> {
                Toast.makeText(
                    activity, message, Toast.LENGTH_SHORT
                ).show()
            }
            3000 -> {
                Log.d("Token Error", message)
            }
            else -> {
                Log.d("MODIFY_failure_noCode", code.toString())
            }
        }
    }
}