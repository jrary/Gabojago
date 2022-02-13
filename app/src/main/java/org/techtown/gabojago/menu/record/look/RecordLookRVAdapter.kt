package org.techtown.gabojago.menu.record.look

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.gabojago.databinding.ItemLookPickBinding
import org.techtown.gabojago.menu.record.recordRetrofit.FolderRecordResultList
import java.util.*

class RecordLookRVAdapter(private val pickedList: ArrayList<FolderRecordResultList>): RecyclerView.Adapter<RecordLookRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemLookPickBinding =
            ItemLookPickBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pickedList[position])
    }

    inner class ViewHolder(val binding: ItemLookPickBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pickedItem: FolderRecordResultList) {
            binding.resultPickTimeTv.text = pickedItem.creatAt
            binding.resultPickTypeTv.text = pickedItem.randomResultType
            binding.resultPickContentsTv.text = pickedItem.randomResultContent
            if(pickedItem.randomResultType == "A"){
                binding.resultPickTypeTv.text = "돌려돌려 돌림판"
            }else if(pickedItem.randomResultType == "B"){
                binding.resultPickTypeTv.text = "N시 방향"
            }else if(pickedItem.randomResultType == "C"){
                binding.resultPickTypeTv.text = "컬러박스 뽑기"
            }else if(pickedItem.randomResultType == "D"){
                binding.resultPickTypeTv.text = "빙글빙글 숫자 뽑기"
            }
        }
    }

    override fun getItemCount(): Int = pickedList.size
}