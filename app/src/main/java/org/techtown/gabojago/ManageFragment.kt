package org.techtown.gabojago

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.techtown.gabojago.databinding.FragmentManageBinding

class ManageFragment : Fragment() {
    lateinit var binding: FragmentManageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageBinding.inflate(layoutInflater)

        binding.manageNicknameModifyBtn.setOnClickListener {
            binding.manageNicknameModifyBtn.visibility = View.GONE
            binding.manageNicknameCompBtn.visibility = View.VISIBLE
        }

        binding.manageNicknameCompBtn.setOnClickListener {
            binding.manageNicknameModifyBtn.visibility = View.VISIBLE
            binding.manageNicknameCompBtn.visibility = View.GONE
            binding.manageNicknameEt.setText(binding.manageNicknameEt.text.toString())
        }

        return binding.root
    }
}