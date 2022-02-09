



package org.techtown.gabojago.menu.record.calendar
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

class CalendarActivity :AppCompatActivity(), NicknameAdventureView, AdventureTimeView {
    lateinit var binding: ActivityCalendarBinding
    var viewDate = ""
    var minus = 0
    var userJoinDate = ""
    var yearMonth = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        init()
        setMonth()
        monthClick()

        val gridLayoutManager = GridLayoutManager(this, 7)
        binding.calendarGridview.layoutManager = gridLayoutManager

        val calendarAdapter = CalendarAdapter(viewDate)
        binding.calendarGridview.adapter = calendarAdapter

        binding.calendarGridview.addItemDecoration(HorizontalItemDecorator( 28))

        val userJwt = getJwt(this, "userJwt")
        val now: Long = System.currentTimeMillis()
        val date = Date(now)
        val dateFormat2 = SimpleDateFormat("yyyyMM", Locale("ko", "KR"))
        yearMonth = dateFormat2.format(date).toInt()
        val calendarService = CalendarService()
        calendarService.setNicknameAdventureView(this@CalendarActivity)
        calendarService.setAdventureTimeView(this@CalendarActivity)
        calendarService.getNicknameAdventure(userJwt)
        calendarService.getAdventureTime(userJwt,yearMonth)

    }

    private fun init() {
        binding.calendarDateTv.text= setMonth()
    }

    private fun monthClick() {
        val cal = Calendar.getInstance()
        val dateArray = initDate().split("-").toTypedArray()
        cal.set(dateArray[0].toInt(), dateArray[1].toInt() - 1, 1)
        viewDate = cal.get(Calendar.YEAR)
            .toString() + "-" + (cal.get(Calendar.MONTH) + 1).toString() + "-" + "01"
        val registerDateArray = setRegisterDate(userJoinDate)
        binding.calendarNextTv.setOnClickListener {
            if (cal.get(Calendar.YEAR) > dateArray[0].toInt() || (cal.get(Calendar.YEAR) == dateArray[0].toInt() && cal.get(
                    Calendar.MONTH + 1) > dateArray[1].toInt())
            ) {
                Toast.makeText(
                    this, "기록이 없는 달이야!", Toast.LENGTH_SHORT
                ).show()
            } else {
                cal.add(Calendar.MONTH, +1)
            }
            binding.calendarDateTv.text = cal.get(Calendar.YEAR).toString() + ", " + (cal.get(
                Calendar.MONTH) + 1).toString() + "월"
            viewDate = cal.get(Calendar.YEAR)
                .toString() + "-" + (cal.get(Calendar.MONTH) + 1).toString() + "-" + "01"
            val calendarAdapter = CalendarAdapter(viewDate)
            binding.calendarGridview.adapter = calendarAdapter
        }

        binding.calendarPreviewTv.setOnClickListener {
            if ((cal.get(Calendar.YEAR) > registerDateArray[0].toInt() && cal.get(Calendar.MONTH) == 11) || (cal.get(
                    Calendar.YEAR) == registerDateArray[0].toInt() && cal.get(Calendar.MONTH) + 1 <= registerDateArray[1].toInt())
            ) {
                Toast.makeText(
                    this, "기록이 없는 달이야!", Toast.LENGTH_SHORT
                ).show()
            } else {
                cal.add(Calendar.MONTH, -1)
            }
            binding.calendarDateTv.text = cal.get(Calendar.YEAR)
                .toString() + ", " + (cal.get(Calendar.MONTH) + 1).toString() + "월"
            viewDate = cal.get(Calendar.YEAR)
                .toString() + "-" + (cal.get(Calendar.MONTH) + 1).toString() + "-" + "01"
            val calendarAdapter = CalendarAdapter(viewDate)
            binding.calendarGridview.adapter = calendarAdapter
        }
    }

    private fun setMonth() : String{
        val now: Long = System.currentTimeMillis()
        val date = Date(now)
        val dateFormat = SimpleDateFormat("yyyy, MM월", Locale("ko", "KR"))
        val stringDate = dateFormat.format(date)
        return stringDate
    }

    fun initDate() : String{
        val now: Long = System.currentTimeMillis()
        val date = Date(now)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale("ko", "KR"))
        val stringDate = dateFormat.format(date)
        return stringDate
    }

    private fun setRegisterDate(registerDate :String) : Array<String> {
        val dateArray = registerDate.split("-").toTypedArray()
        return dateArray
    }

    override fun onNicknameAdventureSuccess(userNicknameAdventure: NicknameAdventureResult) {
        binding.calendarNameTv.text = userNicknameAdventure.userNicknameAdventure
    }

    override fun onNicknameAdventureFailure(code: Int, message: String) {
        Toast.makeText(
            this, message, Toast.LENGTH_SHORT
        ).show()
    }

    override fun onAdventureTimeSuccess(adventureTime: AdventureTimeResult) {
        userJoinDate = adventureTime.userJoinDate.date
        binding.calendarTotalNumberTv.text = adventureTime.monthlyAdventureTimes.monthlyTimes.toString()
    }

    override fun onAdventureTimeFailure(code: Int, message: String) {
        Toast.makeText(
            this, message, Toast.LENGTH_SHORT
        ).show()
    }
}