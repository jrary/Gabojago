package org.techtown.gabojago.menu.record.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import org.techtown.gabojago.data.SingleRecord
import org.techtown.gabojago.databinding.DialogFolderdeleteBinding

class DialogFolderDelete(private val recordList: ArrayList<SingleRecord>) : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //false로 설정해 주면 화면밖 혹은 뒤로가기 버튼시 다이얼로그라 dismiss 되지 않는다.
        isCancelable = true
    }
    private lateinit var binding: DialogFolderdeleteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogFolderdeleteBinding.inflate(inflater, container, false)
        dialog?.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val dialogDeleteRVAdapter = DialogDeleteRVAdapter()
        binding.dialogFolderdeleteRecyclerview.adapter = dialogDeleteRVAdapter

        val dialogSelectRVAdapter = DialogSelectRVAdapter(recordList)
        binding.dialogDeleteRecyclerview.adapter = dialogSelectRVAdapter


        return binding.root
    }
}