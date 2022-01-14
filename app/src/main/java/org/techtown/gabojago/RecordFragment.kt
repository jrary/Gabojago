package org.techtown.gabojago

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.techtown.gabojago.databinding.FragmentRecordBinding

class RecordFragment : Fragment() {

    lateinit var binding: FragmentRecordBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecordBinding.inflate(inflater, container, false)

        binding.recordMonthTv.setOnClickListener{
            startActivity(Intent(activity, CalendarActivity::class.java))
        }

        return binding.root
    }

}