package org.techtown.gabojago.menu.record

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.techtown.gabojago.main.MainActivity
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.FragmentFolderrecordBinding
import org.techtown.gabojago.main.getJwt
import org.techtown.gabojago.menu.record.recordRetrofit.*

//폴더 기록하기(기록 및 수정) 프래그먼트
class FolderRecordFragment(private val hasRecording:Boolean,private val folderIdx :Int, private val resultList:ArrayList<InFolderListResult>) : Fragment(), FolderRecordingView ,FolderLookView,FolderModifyView{
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
        //조회 api
        recordService.getFolderLook(userJwt, folderIdx)
        //폴더 내 항목 리사이클러뷰
        val recordFolderResultRVAdapter = RecordFolderResultRVAdapter(true, resultList)
        binding.recordResultRecyclerview.adapter = recordFolderResultRVAdapter

        //기록되어있으면 수정하기 api 기록되어있지 않으면 기록하기 api
        if(hasRecording){
            //완료버튼 누를 시
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
            //완료 text 누를 시
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
        }else{
            //기록없을때 완료버튼 누를 시
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
            //기록 없을때 완료 text 누를 시
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

        //기록내용 edittext 쓰는 동안 글자수 세기
        binding.folderRecordWriteEt.addTextChangedListener(object: TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.folderRecordCharactersnumTv.text = "0 / 1000"
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var userinput = binding.folderRecordWriteEt.text.toString()
                binding.folderRecordCharactersnumTv.text = userinput.length.toString() + " / 1000"
            }

            override fun afterTextChanged(s: Editable?) {
                var userinput = binding.folderRecordWriteEt.text.toString()
                binding.folderRecordCharactersnumTv.text = userinput.length.toString() + " / 1000"
            }

        })


        clickevent()
        init()

        return binding.root
    }

    //뒤로가기 버튼
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

    //하단바 숨기는 함수
    fun hideBottomNavigation(bool: Boolean) {
        val bottomNavigation: BottomNavigationView = requireActivity().findViewById(R.id.main_bnv)
        if (bool == true)
            bottomNavigation.visibility = View.GONE
        else
            bottomNavigation.visibility = View.VISIBLE
    }

    //폴더기록하기 성공
    override fun onFolderRecordingSuccess() {
        Log.e("폴더기록","성공")
    }

    //폴더기록하기 실패
    override fun onFolderRecordingFailure(code: Int, message: String) {
        Toast.makeText(
            activity, message, Toast.LENGTH_SHORT
        ).show()
    }

    //폴더기록조회 성공
    override fun onFolderLookSuccess(result: FolderLookResult) {
        try {
            binding.folderRecordTitleTv.setText(result.folderContentResult.recordingTitle)
            binding.folderRecordStarscore.rating = result.folderContentResult.recordingStar.toFloat()
            binding.folderRecordWriteEt.setText(result.folderContentResult.recordingContent)


        } catch (e: NullPointerException) {
            binding.folderRecordTitleTv.setText("제목을 입력해줘!")
            binding.folderRecordStarscore.rating = 2.5F
            binding.folderRecordWriteEt.setText("내용을 입력해줘!")
        }
    }

    //폴더기록조회 실패
    override fun onFolderLookFailure(code: Int, message: String) {
        Toast.makeText(
            activity, message, Toast.LENGTH_SHORT
        ).show()
    }

    //폴더기록수정 성공
    override fun onFolderModifySuccess() {
        Log.e("폴더기록","성공")
    }

    //폴더기록수정 실패
    override fun onFolderModifyFailure(code: Int, message: String) {
        Toast.makeText(
            activity, message, Toast.LENGTH_SHORT
        ).show()
    }

}