package org.techtown.gabojago.optionPopup

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import org.techtown.gabojago.databinding.ItemRecordResultBinding
import org.techtown.gabojago.databinding.ItemWheelOptionBinding

class WheelOptionRVAdapter(private val optionList: ArrayList<WheelOptionData>, Act: Context): RecyclerView.Adapter<WheelOptionRVAdapter.ViewHolder>() {

    private lateinit var act: Intent
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
        val binding: ItemWheelOptionBinding =
            ItemWheelOptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        act = Intent()

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WheelOptionRVAdapter.ViewHolder, position: Int) {
       // holder.bind()
        holder.binding.itemRecordSizeTv.setOnClickListener {

        }
    }

    inner class ViewHolder(val binding: ItemWheelOptionBinding) :
        RecyclerView.ViewHolder(binding.root) {
         //   fun onItemClicked()
    }
}