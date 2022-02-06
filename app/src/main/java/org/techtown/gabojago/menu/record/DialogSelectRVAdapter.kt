package org.techtown.gabojago.menu.record

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.gabojago.R
import org.techtown.gabojago.data.SingleRecord
import org.techtown.gabojago.databinding.ItemRecordResultBinding

class DialogSelectRVAdapter(private val recordList: ArrayList<SingleRecord>): RecyclerView.Adapter<DialogSelectRVAdapter.ViewHolder>() {

    //클릭 인터페이스
    interface MyItemClickListener {
    }

    private lateinit var mItemClickListener: MyItemClickListener



    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    //뷰홀더 생성->호출되는 함수->아이템 뷰 객체를 만들어서 뷰홀더에 던져줌
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemRecordResultBinding =
            ItemRecordResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    //뷰홀더에 데이터를 바인딩해줘야 할 때마다 호출되는 함수 => 엄청나게 많이 호출
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recordList[position])
        holder.itemView.setOnClickListener {
            if(!recordList[position].isSelected!!) {
                recordList[position].isSelected= true
                holder.binding.itemRecordRectangleIv.setBackgroundResource(R.drawable.rectangle_gray_select)
            }else{
                recordList[position].isSelected= false
                holder.binding.itemRecordRectangleIv.setBackgroundResource(R.drawable.rectangle_gray)
            }
        }
    }

    //뷰홀더
    inner class ViewHolder(val binding: ItemRecordResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(record: SingleRecord) {
            binding.itemRecordResultTv.text = record.resultType
            binding.itemRecordCircleIv.setImageResource(record.coverImg!!)
            binding.itemRecordTitleIv.setImageResource(record.typeImg!!)
        }

    }

    override fun getItemCount(): Int =recordList.size

}