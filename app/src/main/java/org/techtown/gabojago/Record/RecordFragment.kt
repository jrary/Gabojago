package org.techtown.gabojago.Record

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.techtown.gabojago.databinding.FragmentRecordBinding
import java.text.SimpleDateFormat
import java.util.*

class RecordFragment : Fragment() {

    lateinit var binding: FragmentRecordBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecordBinding.inflate(inflater, container, false)

        binding.recordWeekRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val recordWeekRVAdapter = RecordWeekRVAdapter()
        binding.recordWeekRecyclerview.adapter = recordWeekRVAdapter

        val recordResultRVAdapter = RecordResultRVAdapter()
        binding.recordResultRecyclerview.adapter = recordResultRVAdapter

        binding.recordMonthTv.setOnClickListener{
            startActivity(Intent(activity, CalendarActivity::class.java))
        }
        init()

        return binding.root
    }
    fun init() {
        binding.recordDateTv.setText(setdate())
        binding.recordMonthTv.setText(setMonth())
    }

    fun setdate() : String{
        val now: Long = System.currentTimeMillis()
        val date = Date(now)
        val dateFormat = SimpleDateFormat("yyyy년MM월dd일", Locale("ko", "KR"))
        val stringDate = dateFormat.format(date)

        return stringDate
    }

    fun setMonth() : String{
        val now: Long = System.currentTimeMillis()
        val month = Date(now)
        val monthFormat = SimpleDateFormat("< MM월", Locale("ko", "KR"))
        val stringMonth = monthFormat.format(month)

        return stringMonth
    }

}