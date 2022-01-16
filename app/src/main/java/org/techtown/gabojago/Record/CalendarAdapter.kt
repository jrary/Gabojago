package org.techtown.gabojago.Record

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.gabojago.databinding.ItemCalendarGridviewBinding
import java.text.SimpleDateFormat
import java.util.*

class CalendarAdapter : RecyclerView.Adapter <CalendarAdapter.ViewHolder>() {

    private val days = ArrayList<String>()

    //뷰홀더 생성->호출되는 함수->아이템 뷰 객체를 만들어서 뷰홀더에 던져줌
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCalendarGridviewBinding = ItemCalendarGridviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    //뷰홀더에 데이터를 바인딩해줘야 할 때마다 호출되는 함수 => 엄청나게 많이 호출
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)

    }

    //뷰홀더
    inner class ViewHolder(val binding: ItemCalendarGridviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            setEmptyDate(setDate())
            binding.itemGridviewTv.text = days[position]

        }

    }


    override fun getItemCount(): Int {
        return 42
    }



    private fun setEmptyDate(eventDate: String) {
        val dateArray = eventDate.split("-").toTypedArray()
        val cal = Calendar.getInstance()
        cal.set(dateArray[0].toInt(), dateArray[1].toInt() - 1, 1)
        val dayNum: Int = cal.get(Calendar.DAY_OF_WEEK)
        //1일 - 요일 매칭 시키기 위해 공백 add
        for (i in 1 until dayNum) {
            days.add("")
        }
        setCalendarDate(cal.get(Calendar.MONTH) + 1)
    }

    private fun setCalendarDate(month: Int) {
        val cal = Calendar.getInstance()
        cal.set(Calendar.MONTH, month - 1)
        for (i in 0 until cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            days.add(""+(i+1))
        }
    }

    fun setDate() : String{
        val now: Long = System.currentTimeMillis()
        val date = Date(now)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale("ko", "KR"))
        val stringDate = dateFormat.format(date)

        return stringDate
    }

}

