package org.techtown.gabojago.randomPick

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.techtown.gabojago.MainActivity
import org.techtown.gabojago.databinding.FragmentWheelBinding
import org.techtown.gabojago.optionPopup.WheelOptionActivity


class WheelFragment : Fragment() {
    lateinit var binding: FragmentWheelBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWheelBinding.inflate(layoutInflater)

        binding.wheelOptionBtn.setOnClickListener {
          //  startActivity(Intent(this, WheelOptionActivity::class.java))
        }


        return binding.root
    }
}