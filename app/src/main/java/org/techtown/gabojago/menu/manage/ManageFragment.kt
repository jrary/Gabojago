package org.techtown.gabojago.menu.manage

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
            binding.manageNicknameView.visibility = View.VISIBLE
            binding.manageNicknameEt.setText(binding.manageNicknameTv.text.toString())
        }

        binding.manageNicknameCompBtn.setOnClickListener {
            binding.manageNicknameView.visibility = View.GONE
            binding.manageNicknameTv.text = binding.manageNicknameEt.text.toString()
        }

        return binding.root
    }
}