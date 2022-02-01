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

        binding.manageNicknameCompBtn.setOnClickListener {
            //binding.test.text = binding.manageNicknameEt.text.toString()
        }

        return binding.root
    }
}