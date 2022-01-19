package org.techtown.gabojago.optionPopup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.gabojago.databinding.ItemRecordResultBinding

class WheelOptionRVAdapter(private val optionList: ArrayList<WheelOptionData>): RecyclerView.Adapter<WheelOptionRVAdapter.ViewHolder>() {
    //클릭 인터페이스
    interface MyItemClickListener {
        fun onItemClick()
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun getItemCount() = optionList.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ItemRecordResultBinding =
            ItemRecordResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WheelOptionRVAdapter.ViewHolder, position: Int) {
        holder.bind()
        holder.binding.itemRecordPecilIv.setOnClickListener {
            mItemClickListener.onItemClick()
        }
    }

    inner class ViewHolder(val binding: ItemRecordResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
        }

    }
}