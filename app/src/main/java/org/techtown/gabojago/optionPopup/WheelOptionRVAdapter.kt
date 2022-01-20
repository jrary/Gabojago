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

class WheelOptionRVAdapter(private val optionList: ArrayList<WheelOptionData>): RecyclerView.Adapter<WheelOptionRVAdapter.ViewHolder>() {

    private val activity : WheelOptionActivity = mContext as WheelOptionActivity

    //클릭 인터페이스
    public interface MyItemClickListener {
        fun onItemClick(v: View, position: Int)
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

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WheelOptionRVAdapter.ViewHolder, position: Int) {
        holder.bind(optionList[position])
        holder.binding.itemRecordSizeTv.setOnClickListener {
            //startActivity(Intent(activity, WheelSelectActivity::class.java))
        }
    }

    inner class ViewHolder(val binding: ItemWheelOptionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(wheelOption: WheelOptionData){
         //   binding.itemRecordNameEt.getText.toString() = wheelOption.name
            binding.itemRecordSizeTv.text = wheelOption.num.toString()
            binding.itemRecordProbTv.text = wheelOption.prob.toString()
        }
        fun onItemClicked() {

        }
    }
}