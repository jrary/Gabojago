package org.techtown.gabojago.menu.record.calender

import HorizontalItemDecorator
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import org.techtown.gabojago.databinding.ActivityCalendarBinding
import org.techtown.gabojago.main.getjwt
import org.techtown.gabojago.menu.manage.ManageService
import org.techtown.gabojago.menu.manage.NicknameView
import java.text.SimpleDateFormat
import java.util.*

class CalendarActivity :AppCompatActivity(), NicknameAdventureView {
    lateinit var binding: ActivityCalendarBinding
    var nickNameAdventure: String = "닉네임"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        val gridLayoutManager = GridLayoutManager(this, 7)
        binding.calendarGridview.layoutManager = gridLayoutManager

        val calendarAdapter = CalendarAdapter()
        binding.calendarGridview.adapter = calendarAdapter

        binding.calendarGridview.addItemDecoration(HorizontalItemDecorator( 28))

        val calendarService = CalendarService()
        calendarService.setNicknameAdventureView(this@CalendarActivity)
        nickNameAdventure = calendarService.getNicknameAdventure().toString()

        init()

    }

    private fun init() {
        binding.calendarDateTv.setText(setdate())
    }

    private fun setdate() : String{
        val now: Long = System.currentTimeMillis()
        val date = Date(now)
        val dateFormat = SimpleDateFormat("yyyy, MM월", Locale("ko", "KR"))
        val stringDate = dateFormat.format(date)

        return stringDate
    }

    override fun onNicknameSuccess(userNickname: String) {
        binding.calendarNameTv.text = nickNameAdventure
    }

    override fun onNicknameFailure(code: Int, message: String) {
        Toast.makeText(
            this, "Failed.", Toast.LENGTH_SHORT
        ).show()
    }
}