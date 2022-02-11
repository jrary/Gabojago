package org.techtown.gabojago.menu.record.calendar

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.NonDisposableHandle.parent
import org.techtown.gabojago.databinding.ItemCalendarGridviewBinding
import java.text.SimpleDateFormat
import java.util.*

class CalendarAdapter(private val viewDate: String) : RecyclerView.Adapter <CalendarAdapter.ViewHolder>(), AdventureTimeView {

    private val days = ArrayList<String>()
    val randomresultdateList = ArrayList<String>()
    var month =""
    var year =""
    var todayYear = 0
    var todayMonth = 0
    var todayDate = 0

    //뷰홀더 생성->호출되는 함수->아이템 뷰 객체를 만들어서 뷰홀더에 던져줌
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCalendarGridviewBinding = ItemCalendarGridviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        setEmptyDate(viewDate)
        return ViewHolder(binding)
    }

    //뷰홀더에 데이터를 바인딩해줘야 할 때마다 호출되는 함수 => 엄청나게 많이 호출
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)

    }

    //뷰홀더
    inner class ViewHolder(val binding: ItemCalendarGridviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.itemGridviewTv.text = days[position]

            if(randomresultdateList.size != 0) {
                for (i in 0 until randomresultdateList.size) {
                    val creatDateArray = setCreatDate(randomresultdateList[i])
                    if (days[position] != "") {
                        today()
                        if (todayYear == creatDateArray[0].toInt() && todayMonth == creatDateArray[1].toInt() && days[position].toInt() == creatDateArray[2].toInt()) {
                            binding.itemGridviewRecordIv.visibility = View.VISIBLE
                        } else {
                            binding.itemGridviewRecordIv.visibility = View.GONE
                        }

                    }

                }
            }

            if(days[position]!="") {
                today()
                if (todayYear == year.toInt()&&todayMonth==month.toInt() && todayDate==days[position].toInt()) {
                    binding.itemGridviewTodayIv.visibility = View.VISIBLE
                } else {
                    binding.itemGridviewTodayIv.visibility = View.GONE
                }

            }

        }

    }


    override fun getItemCount(): Int = setEmptyDate(viewDate)



    private fun setEmptyDate(eventDate: String) :Int{
        val dateArray = eventDate.split("-").toTypedArray()
        val cal = Calendar.getInstance()
        cal.set(dateArray[0].toInt(), dateArray[1].toInt() - 1, 1)
        val dayNum: Int = cal.get(Calendar.DAY_OF_WEEK)
        //1일 - 요일 매칭 시키기 위해 공백 add
        for (i in 1 until dayNum) {
            days.add("")
        }
        val daySize = setCalendarDate(cal.get(Calendar.MONTH) + 1)
        month =(cal.get(Calendar.MONTH) + 1).toString()
        year = cal.get(Calendar.YEAR).toString()
        return (dayNum+daySize)
    }

    private fun setCalendarDate(month: Int) : Int{
        val cal = Calendar.getInstance()
        cal.set(Calendar.MONTH, month - 1)
        for (i in 0 until cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            days.add(""+(i+1))
        }
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    private fun today() {
        val now: Long = System.currentTimeMillis()
        val date = Date(now)
        val yearFormat = SimpleDateFormat("yyyy", Locale("ko", "KR"))
        val monthFormat = SimpleDateFormat("MM", Locale("ko", "KR"))
        val dateFormat = SimpleDateFormat("dd", Locale("ko", "KR"))
        todayYear = yearFormat.format(date).toInt()
        todayMonth = monthFormat.format(date).toInt()
        todayDate = dateFormat.format(date).toInt()
    }

    private fun setCreatDate(creatDate :String) : Array<String> {
        val dateArray = creatDate.split("-","T").toTypedArray()
        return dateArray
    }

    override fun onAdventureTimeSuccess(adventureTime: AdventureTimeResult) {
        if(adventureTime.randomresultdateList.size!=0) {
            for (i in 0 until adventureTime.randomresultdateList.size) {
                randomresultdateList.add(adventureTime.randomresultdateList[i].createAt)
            }
        }
    }

    override fun onAdventureTimeFailure(code: Int, message: String) {
        Log.e("모험횟수 받아오기 오류",message)
    }

//    fun setDate() : String{
//        val now: Long = System.currentTimeMillis()
//        val date = Date(now)
//        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale("ko", "KR"))
//        val stringDate = dateFormat.format(date)
//
//        return stringDate
//    }

}

