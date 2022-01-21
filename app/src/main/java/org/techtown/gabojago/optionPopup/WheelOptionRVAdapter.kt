package org.techtown.gabojago.optionPopup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.gabojago.databinding.ItemWheelOptionBinding
import java.util.ArrayList

class WheelOptionRVAdapter(var optionList: ArrayList<WheelOptionData>): RecyclerView.Adapter<WheelOptionRVAdapter.ViewHolder>() {

    //클릭 인터페이스
    interface MyItemClickListener {
        fun onItemClick()
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    //뷰홀더 생성->호출되는 함수->아이템 뷰 객체를 만들어서 뷰홀더에 던져줌
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemWheelOptionBinding =
            ItemWheelOptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    //뷰홀더에 데이터를 바인딩해줘야 할 때마다 호출되는 함수 => 엄청나게 많이 호출
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
        holder.binding.itemRecordSizeTv.setOnClickListener {
            mItemClickListener.onItemClick()
        }
    }

    //뷰홀더
    inner class ViewHolder(val binding: ItemWheelOptionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
        }

    }

    override fun getItemCount(): Int {
        return 30
    }

}