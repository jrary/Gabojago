package org.techtown.gabojago.menu.record

import android.content.Context
import android.location.Location
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.NonDisposableHandle.parent
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.ActivityCalendarBinding.inflate
import org.techtown.gabojago.databinding.ItemRecordFoldernameBinding
import org.techtown.gabojago.databinding.PopupMenuBinding



class RecordFolderResultNameRVAdapter: RecyclerView.Adapter<RecordFolderResultNameRVAdapter.ViewHolder>() {

    //클릭 인터페이스
    interface MyItemClickListener {
        fun onItemClickPencil()
        fun onItemView()
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
            mItemClickListener.onItemView()
        }
        holder.binding.folderRecordPecilIv.setOnClickListener {
            mItemClickListener.onItemClickPencil()
        }
        holder.binding.folderRecordFolderIv.setOnClickListener {
            val inflater = LayoutInflater.from(holder.binding.folderRecordFolderIv.context)
            val popup = PopupWindow(inflater.inflate(R.layout.popup_menu, null, false),
                260,
                200,
                true)
            val location=IntArray(2)
            holder.binding.folderRecordFolderIv.getLocationOnScreen(location)


            popup.showAtLocation(holder.binding.folderRecordFolderIv, Gravity.NO_GRAVITY, location[0]-30, location[1]+60)
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