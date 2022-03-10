package org.techtown.gabojago.menu.record

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.storage.FirebaseStorage
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.FragmentFolderrecordBinding
import org.techtown.gabojago.main.MainActivity
import org.techtown.gabojago.main.MyToast
import org.techtown.gabojago.main.getJwt
import org.techtown.gabojago.menu.record.dialog.DialogRealFolderrecordDelete
import org.techtown.gabojago.menu.record.recordRetrofit.*
import java.text.SimpleDateFormat
import java.util.*


//폴더 기록하기(기록 및 수정) 프래그먼트
class FolderRecordFragment(private val hasRecording:Boolean,private val folderIdx :Int, private val resultList:ArrayList<InFolderListResult>) : Fragment(), FolderRecordingView ,FolderLookView,FolderModifyView{
    lateinit var binding: FragmentFolderrecordBinding
    val imgFileName = ArrayList<String>()
    val uriList = ArrayList<Uri?>()
    val urlList = ArrayList<String>()
    val imageList = ArrayList<String>()
    var fbStorage : FirebaseStorage? = null
    val recordService = RecordService()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        uriList.clear()
        binding = FragmentFolderrecordBinding.inflate(inflater, container, false)

        val userJwt = getJwt(requireContext(), "userJwt")
        fbStorage = FirebaseStorage.getInstance()

        recordService.setFolderLookView(this@FolderRecordFragment)
        //조회 api
        recordService.getFolderLook(userJwt, folderIdx)

        binding.folderRecordTrash.setOnClickListener{
            val dialogRealFolderrecordDelete = DialogRealFolderrecordDelete(folderIdx)
            dialogRealFolderrecordDelete.show((context as MainActivity).supportFragmentManager,"dialog")
            binding.folderRecordBlurView.visibility = View.VISIBLE
            binding.folderRecordBlurView2.visibility = View.VISIBLE
            dialogRealFolderrecordDelete.setOnDismissClickListener(object :
                DialogRealFolderrecordDelete.onDismissListener{
                override fun onDismiss(dialogFragment: DialogFragment) {
                    binding.folderRecordBlurView.visibility = View.GONE
                    binding.folderRecordBlurView2.visibility = View.GONE
                }
            })
        }

        //폴더 내 항목 리사이클러뷰
        val recordFolderResultRVAdapter = RecordFolderResultRVAdapter(true, resultList)
        binding.recordResultRecyclerview.adapter = recordFolderResultRVAdapter

        //기록되어있으면 수정하기 api 기록되어있지 않으면 기록하기 api
        if(hasRecording){
            //기록있을때 카메라 버튼 누를시
            binding.folderRecordPictureIv.setOnClickListener{
                imageList.clear()
                funImageDelete()
                fromGallery()
            }

            //완료버튼 누를 시
            binding.folderRecordCompleteView.setOnClickListener {
                val recordService = RecordService()
                recordService.setFolderModifyView(this@FolderRecordFragment)
                val userJwt = getJwt(requireContext(), "userJwt")
                recordService.putFolderModify(userJwt,  FolderModifyRequest(
                    binding.folderRecordStarscore.rating.toDouble(),
                    binding.folderRecordWriteEt.text.toString(),
                    binding.folderRecordTitleTv.text.toString(),
                    urlList
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
                    urlList
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
            //기록없을때 카메라 버튼 누를시
            binding.folderRecordPictureIv.setOnClickListener{
                fromGallery()
            }

            //기록없을때 완료버튼 누를 시
            binding.folderRecordCompleteView.setOnClickListener {
                val recordService = RecordService()
                recordService.setFolderRecordingView(this@FolderRecordFragment)
                val userJwt = getJwt(requireContext(), "userJwt")
                recordService.putFolderRecord(userJwt, folderIdx, FolderRecordingRequest(
                    binding.folderRecordStarscore.rating.toDouble(),
                    binding.folderRecordWriteEt.text.toString(),
                    binding.folderRecordTitleTv.text.toString(),
                    urlList
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
                    binding.folderRecordTitleTv.text.toString(),
                    urlList
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

        ActivityCompat.requestPermissions(requireActivity(),
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
            1)

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

    private fun fromGallery() {
        val intent  = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent,101)
    }

    private fun init() {
        hideBottomNavigation(true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                101 -> {
                    if (ContextCompat.checkSelfPermission(requireContext(),
                            android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    ) {
                        if (data == null) {   // 어떤 이미지도 선택하지 않은 경우
                            Toast.makeText(requireContext(), "이미지를 선택해줘!", Toast.LENGTH_LONG)
                                .show();
                        } else {   // 이미지를 하나라도 선택한 경우
                            if (data.clipData == null) {     // 이미지를 하나만 선택한 경우
                                Log.e("single choice: ", data?.data.toString())
                                val imageUri = data?.data
                                uriList.add(imageUri)
                                imgFileName.add("IMAGE_" + timeStamp +"0"+ "_.png")
                                val recordPictureRVAdapter = RecordPictureRVAdapter(uriList,imageList)
                                binding.folderRecordPictureRecyclerview.adapter =
                                    recordPictureRVAdapter
                            } else {      // 이미지를 여러장 선택한 경우
                                var clipData = data?.clipData
                                Log.e("clipData", clipData?.itemCount.toString())

                                if (clipData?.itemCount != null && clipData?.itemCount > 10) {   // 선택한 이미지가 11장 이상인 경우
                                    Toast.makeText(requireContext(),
                                        "사진은 10장까지 선택 가능해!", Toast.LENGTH_LONG).show()
                                } else {   // 선택한 이미지가 1장 이상 10장 이하인 경우
                                    Log.e("1장이상10장이하", "multiple choice")
                                    for (i in 0 until clipData?.itemCount!!) {
                                        var imageUri =
                                            clipData.getItemAt(i).uri // 선택한 이미지들의 uri를 가져온다.
                                        try {
                                            uriList.add(imageUri)  //uri를 list에 담는다.
                                            imgFileName.add("IMAGE_" + timeStamp +i.toString()+ "_.png")
                                        } catch (e: Exception) {
                                            Log.e("선택에러", "File select error", e)
                                        }
                                    }
                                    funImageUpload()
                                    binding.folderRecordPicturenumTv.text = uriList.size.toString()+"/10"
                                    val recordPictureRVAdapter = RecordPictureRVAdapter(uriList,imageList)
                                    binding.folderRecordPictureRecyclerview.adapter =
                                        recordPictureRVAdapter
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun funImageUpload() {
        for(i in 0 until uriList.size){
            urlList.add(i,"")
        }
        if (uriList.isNotEmpty()) {
            for(i in 0 until uriList.size) {
                fbStorage?.reference?.child("images")?.child(imgFileName[i])?.putFile(uriList[i]!!)
                    ?.addOnSuccessListener {
                        it.storage.downloadUrl.addOnCompleteListener {
                            urlList.set(i,it.result.toString())
                            Log.e("url", urlList.toString())
                            Toast.makeText(requireContext(), "이미지가 업로드 됐어!", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            }
        }
    }

    private fun funImageDelete() {
        if (imageList.isNotEmpty()) {
            for(i in 0 until imageList.size) {
                if(imageList[i]!=null) {
                    fbStorage?.getReferenceFromUrl(imageList[i])?.delete()
                        ?.addOnSuccessListener {
                            Toast.makeText(requireContext(), "이미지가 삭제 됐어!", Toast.LENGTH_SHORT)
                                .show()
                        }
                }
            }
        }
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
        MyToast.createToast(
            requireContext(), message
        )?.show()
    }

    //폴더기록조회 성공
    override fun onFolderLookSuccess(result: FolderLookResult) {
        try {
            binding.folderRecordTitleTv.setText(result.folderContentResult.recordingTitle)
            binding.folderRecordStarscore.rating = result.folderContentResult.recordingStar.toFloat()
            binding.folderRecordWriteEt.setText(result.folderContentResult.recordingContent)

            for(i in 0 until result.folderImgListResult.size){
                imageList.add(i,result.folderImgListResult[i].recordingImgUrl)

            }
            binding.folderRecordPicturenumTv.text = result.folderImgListResult.size.toString()+"/10"
            Log.e("for문",imageList.toString())
            val recordPictureRVAdapter = RecordPictureRVAdapter(uriList,imageList)
            binding.folderRecordPictureRecyclerview.adapter =
                recordPictureRVAdapter



        } catch (e: NullPointerException) {
            binding.folderRecordTitleTv.setText("제목을 입력해줘!")
            binding.folderRecordStarscore.rating = 2.5F
            binding.folderRecordWriteEt.setText("내용을 입력해줘!")
        }
    }

    //폴더기록조회 실패
    override fun onFolderLookFailure(code: Int, message: String) {
        MyToast.createToast(
            requireContext(), message
        )?.show()
    }

    //폴더기록수정 성공
    override fun onFolderModifySuccess() {
        Log.e("폴더기록","성공")
    }

    //폴더기록수정 실패
    override fun onFolderModifyFailure(code: Int, message: String) {
        MyToast.createToast(
            requireContext(), message
        )?.show()
    }

}