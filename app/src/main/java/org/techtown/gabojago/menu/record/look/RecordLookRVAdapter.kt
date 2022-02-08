package org.techtown.gabojago.menu.record.look

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.gabojago.databinding.ItemLookPickBinding
import java.util.*

class RecordLookRVAdapter(var pickedList: ArrayList<RecordLook>): RecyclerView.Adapter<RecordLookRVAdapter.ViewHolder>() {

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
        fun bind(pickedItem: RecordLook) {
            binding.resultPickTimeTv.text = pickedItem.time
            binding.resultPickTypeTv.text = pickedItem.type
            binding.resultPickContentsTv.text = pickedItem.contents
        }
    }

    override fun getItemCount(): Int = pickedList.size
}