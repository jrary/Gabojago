package org.techtown.gabojago.randomPick.wheel

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.techtown.gabojago.databinding.ItemWheelOptionBinding
import org.techtown.gabojago.optionPopup.WheelOptionData
import java.util.ArrayList

class WheelOptionRVAdapter(var optionList: ArrayList<WheelOptionData>): RecyclerView.Adapter<WheelOptionRVAdapter.ViewHolder>() {

    //클릭 인터페이스
    interface MyItemClickListener {
        fun onItemClick(position: Int)
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
        holder.bind(optionList[position])
        holder.binding.itemRecordSizeTv.setOnClickListener {
            mItemClickListener.onItemClick(position)
            Log.d("RVposition - ", position.toString())
            holder.bind(optionList[position])
        }
        holder.binding.itemRecordMinus.setOnClickListener{
            removeItem(position)
        }
    }

    //뷰홀더
    inner class ViewHolder(val binding: ItemWheelOptionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(wheelNum: WheelOptionData) {
            binding.itemRecordNameEt.setText(wheelNum.name)
            binding.itemRecordSizeTv.text = wheelNum.num.toString()
            binding.itemRecordProbTv.text = wheelNum.prob.toString()
        }
    }

    override fun getItemCount(): Int = optionList.size

    private fun removeItem(position: Int){
        optionList.removeAt(position)
        notifyDataSetChanged()
    }

    fun addItem(prob: Int){
        optionList.add(WheelOptionData("옵션" + (optionList.size + 1), 1, prob))
        notifyDataSetChanged()
    }

    fun updateRecordSize(position: Int, newNum : Int) {
        optionList[position].num = newNum
        notifyDataSetChanged()
    }
}