package org.techtown.gabojago.menu.record

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.gson.Gson
import org.techtown.gabojago.R
import org.techtown.gabojago.data.SingleRecord
import org.techtown.gabojago.databinding.DialogFolderselectBinding
import org.techtown.gabojago.databinding.ItemRecordResultBinding

class DialogFolderSelect(private val recordList: ArrayList<SingleRecord>) : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //false로 설정해 주면 화면밖 혹은 뒤로가기 버튼시 다이얼로그라 dismiss 되지 않는다.
        isCancelable = true
    }
    private lateinit var binding: DialogFolderselectBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogFolderselectBinding.inflate(inflater, container, false)
        dialog?.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val dialogSelectRVAdapter = DialogSelectRVAdapter(recordList)
        binding.dialogSelectRecyclerview.adapter = dialogSelectRVAdapter


        return binding.root
    }
}