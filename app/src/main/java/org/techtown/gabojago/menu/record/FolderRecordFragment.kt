package org.techtown.gabojago.menu.record

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.annotations.SerializedName
import org.techtown.gabojago.main.MainActivity
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.FragmentFolderrecordBinding
import org.techtown.gabojago.main.getJwt
import org.techtown.gabojago.menu.record.recordRetrofit.*

class FolderRecordFragment(private val folderIdx :Int, private val resultList:ArrayList<InFolderListResult>) : Fragment(), FolderRecordingView ,FolderLookView,FolderModifyView{
    lateinit var binding: FragmentFolderrecordBinding
    val url= mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFolderrecordBinding.inflate(inflater, container, false)

        val recordService = RecordService()
        recordService.setFolderLookView(this@FolderRecordFragment)

        val userJwt = getJwt(requireContext(), "userJwt")

        recordService.getFolderLook(userJwt, folderIdx)

        val recordFolderResultRVAdapter = RecordFolderResultRVAdapter(true, resultList)
        binding.recordResultRecyclerview.adapter = recordFolderResultRVAdapter


        clickevent()
        init()

        return binding.root
    }

    private fun clickevent(){
        binding.folderRecordBackarrow.setOnClickListener{
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, RecordFragment().apply {
                    arguments = Bundle().apply {
                    }
                })
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }
    }

    private fun init() {
        hideBottomNavigation(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        hideBottomNavigation(false)
    }

    fun hideBottomNavigation(bool: Boolean) {
        val bottomNavigation: BottomNavigationView = requireActivity().findViewById(R.id.main_bnv)
        if (bool == true)
            bottomNavigation.visibility = View.GONE
        else
            bottomNavigation.visibility = View.VISIBLE
    }

    override fun onFolderRecordingSuccess() {
        Log.e("폴더기록","성공")
    }

    override fun onFolderRecordingFailure(code: Int, message: String) {
        Toast.makeText(
            activity, message, Toast.LENGTH_SHORT
        ).show()
    }

    override fun onFolderLookSuccess(result: FolderLookResult) {
        try {
            binding.folderRecordTitleTv.setText(result.folderContentResult.recordingTitle)
            binding.folderRecordStarscore.rating = result.folderContentResult.recordingStar.toFloat()
            binding.folderRecordWriteEt.setText(result.folderContentResult.recordingContent)

            binding.folderRecordCompleteView.setOnClickListener {
                val recordService = RecordService()
                recordService.setFolderModifyView(this@FolderRecordFragment)
                val userJwt = getJwt(requireContext(), "userJwt")
                recordService.putFolderModify(userJwt,  FolderModifyRequest(
                    binding.folderRecordStarscore.rating.toDouble(),
                    binding.folderRecordWriteEt.text.toString(),
                    binding.folderRecordTitleTv.text.toString(),
                ),folderIdx)

                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, RecordFragment().apply {
                        arguments = Bundle().apply {
                        }
                    })
                    .addToBackStack(null)
                    .commitAllowingStateLoss()

            }
            binding.folderRecordCompleteTv.setOnClickListener {
                val recordService = RecordService()
                recordService.setFolderModifyView(this@FolderRecordFragment)
                val userJwt = getJwt(requireContext(), "userJwt")
                recordService.putFolderModify(userJwt,  FolderModifyRequest(
                    binding.folderRecordStarscore.rating.toDouble(),
                    binding.folderRecordWriteEt.text.toString(),
                    binding.folderRecordTitleTv.text.toString(),
                ),folderIdx)

                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, RecordFragment().apply {
                        arguments = Bundle().apply {
                        }
                    })
                    .addToBackStack(null)
                    .commitAllowingStateLoss()

            }
        } catch (e: NullPointerException) {
            binding.folderRecordTitleTv.setText("제목을 입력해줘!")
            binding.folderRecordStarscore.rating = 2.5F
            binding.folderRecordWriteEt.setText("내용을 입력해줘!")

            binding.folderRecordCompleteView.setOnClickListener {
                val recordService = RecordService()
                recordService.setFolderRecordingView(this@FolderRecordFragment)
                val userJwt = getJwt(requireContext(), "userJwt")
                recordService.putFolderRecord(userJwt, folderIdx, FolderRecordingRequest(
                    binding.folderRecordStarscore.rating.toDouble(),
                    binding.folderRecordWriteEt.text.toString(),
                    binding.folderRecordTitleTv.text.toString(),
                    url
                ))

                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, RecordFragment().apply {
                        arguments = Bundle().apply {
                        }
                    })
                    .addToBackStack(null)
                    .commitAllowingStateLoss()

            }
            binding.folderRecordCompleteTv.setOnClickListener {
                val recordService = RecordService()
                recordService.setFolderRecordingView(this@FolderRecordFragment)
                val userJwt = getJwt(requireContext(), "userJwt")
                recordService.putFolderRecord(userJwt, folderIdx, FolderRecordingRequest(
                    binding.folderRecordStarscore.rating.toDouble(),
                    binding.folderRecordWriteEt.text.toString(),
                    binding.folderRecordTitleTv.text.toString(),url
                ))

                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, RecordFragment().apply {
                        arguments = Bundle().apply {
                        }
                    })
                    .addToBackStack(null)
                    .commitAllowingStateLoss()

            }

        }
    }

    override fun onFolderLookFailure(code: Int, message: String) {
        Toast.makeText(
            activity, message, Toast.LENGTH_SHORT
        ).show()
    }

    override fun onFolderModifySuccess() {
        Log.e("폴더기록","성공")
    }

    override fun onFolderModifyFailure(code: Int, message: String) {
        Toast.makeText(
            activity, message, Toast.LENGTH_SHORT
        ).show()
    }

}