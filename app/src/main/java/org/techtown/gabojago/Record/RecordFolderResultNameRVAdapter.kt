package org.techtown.gabojago.Record

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.techtown.gabojago.databinding.ItemRecordFolderBinding
import org.techtown.gabojago.databinding.ItemRecordFoldernameBinding


class RecordFolderResultNameRVAdapter: RecyclerView.Adapter<RecordFolderResultNameRVAdapter.ViewHolder>() {

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
        val binding: ItemRecordFoldernameBinding =
            ItemRecordFoldernameBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    //뷰홀더에 데이터를 바인딩해줘야 할 때마다 호출되는 함수 => 엄청나게 많이 호출
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick()
        }
        holder.binding.folderRecordPecilIv.setOnClickListener {
            mItemClickListener.onItemClick()
        }
    }

    //뷰홀더
    inner class ViewHolder(val binding: ItemRecordFoldernameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val recordFolderResultRVAdapter = RecordFolderResultRVAdapter()
            binding.itemRecordResultRecyclerview.adapter = recordFolderResultRVAdapter

        }

    }

    override fun getItemCount(): Int {
        return 2
    }

}