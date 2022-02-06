



package org.techtown.gabojago.menu.record.calender
import HorizontalItemDecorator
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import org.techtown.gabojago.databinding.ActivityCalendarBinding
import org.techtown.gabojago.main.getJwt
import java.text.SimpleDateFormat
import java.util.*

class CalendarActivity :AppCompatActivity(), NicknameAdventureView {
    lateinit var binding: ActivityCalendarBinding

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

        val userJwt = getJwt(this, "userJwt")
        val calendarService = CalendarService()
        calendarService.setNicknameAdventureView(this@CalendarActivity)
        calendarService.getNicknameAdventure(userJwt)

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

    override fun onNicknameAdventureSuccess(userNicknameAdventure: NicknameAdventureResult) {
        binding.calendarNameTv.text = userNicknameAdventure.userNicknameAdventure
    }

    override fun onNicknameAdventureFailure(code: Int, message: String) {
        Toast.makeText(
            this, "Failed.", Toast.LENGTH_SHORT
        ).show()
    }
}