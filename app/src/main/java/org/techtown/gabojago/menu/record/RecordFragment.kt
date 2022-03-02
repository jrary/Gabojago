package org.techtown.gabojago.menu.record

import HorizontalItemDecorator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.FragmentRecordBinding
import org.techtown.gabojago.databinding.ItemRecordFoldernameBinding
import org.techtown.gabojago.databinding.ItemRecordWeekBinding
import org.techtown.gabojago.main.MainActivity
import org.techtown.gabojago.main.getJwt
import org.techtown.gabojago.menu.record.calendar.CalendarActivity
import org.techtown.gabojago.menu.record.dialog.DialogFolderDelete
import org.techtown.gabojago.menu.record.dialog.DialogFolderModify
import org.techtown.gabojago.menu.record.dialog.DialogFolderSelect
import org.techtown.gabojago.menu.record.dialog.DialogRealBreakup
import org.techtown.gabojago.menu.record.look.RecordLookFragment
import org.techtown.gabojago.menu.record.recordRetrofit.*
import java.text.SimpleDateFormat
import java.util.*

//기록하자 메인화면
class RecordFragment : Fragment(), RecordCountView, SingleResultListView, FolderResultListView{

    lateinit var binding: FragmentRecordBinding
    lateinit var binding2: ItemRecordFoldernameBinding
    lateinit var binding3: ItemRecordWeekBinding

    var records = ArrayList<SingleResultListResult>()
    var folders = ArrayList<FolderResultList>()
    var add: Boolean = true
    val recordService = RecordService()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRecordBinding.inflate(inflater, container, false)
        binding2 = ItemRecordFoldernameBinding.inflate(inflater, container, false)
        binding3 = ItemRecordWeekBinding.inflate(inflater, container, false)

        recordService.setRecordCountView(this@RecordFragment)
        recordService.setSingleResultListView(this@RecordFragment)
        recordService.setFolderResultListView(this@RecordFragment)

        val userJwt = getJwt(requireContext(), "userJwt")
        //캘린더 액티비티에서 값이 넘어왔을 경우
        val specialDate = arguments?.getString("changeDate")
        if (specialDate != null) {
            //날짜, 구분선, 메시지 조정
            binding.recordMonthTv.setText("< "+specialDate.substring(4,6).toInt().toString()+"월")
            binding.recordDateTv.setText(specialDate.substring(0,4)+"년 "+specialDate.substring(4,6).toInt().toString()+"월 "+specialDate.substring(6).toInt().toString()+"일")
            binding.recordDivisionView.visibility = View.VISIBLE
            binding.recordEmptyTv.visibility = View.VISIBLE
            binding.recordNotifyTv.visibility = View.GONE
            //메인화면 레트로핏 다시 불러오기
            recordService.getSingleResultList(userJwt, specialDate)
            recordService.getFolderResultList(userJwt, specialDate)
            //주간캘린더 다시 불러오기
            binding.recordWeekRecyclerview.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            val recordWeekRVAdapter = RecordWeekRVAdapter(specialDate)
            binding.recordWeekRecyclerview.adapter = recordWeekRVAdapter
            //주간캘린더 클릭이벤트
            recordWeekRVAdapter.setMyItemClickListener(object :
                RecordWeekRVAdapter.MyItemClickListener {
                override fun onItemClick(day: String) {
                    binding.recordDivisionView.visibility = View.VISIBLE
                    binding.recordEmptyTv.visibility= View.VISIBLE
                    binding.recordNotifyTv.visibility = View.GONE

                    //날짜별로 메인화면 레트로핏 다시 불러오기
                    recordService.getSingleResultList(userJwt,day)
                    recordService.getFolderResultList(userJwt,day)
                }
            })

        }else{
            //캘린더액티비티에서 값이 넘어오지 않는경우(오늘날짜 default)
            init()
            val now: Long = System.currentTimeMillis()
            val date = Date(now)
            val dateFormat = SimpleDateFormat("yyyyMMdd", Locale("ko", "KR"))
            val stringDate = dateFormat.format(date)
            recordService.getSingleResultList(userJwt,stringDate)
            recordService.getFolderResultList(userJwt,stringDate)

            binding.recordWeekRecyclerview.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            val recordWeekRVAdapter = RecordWeekRVAdapter(stringDate)
            binding.recordWeekRecyclerview.adapter = recordWeekRVAdapter

            recordWeekRVAdapter.setMyItemClickListener(object :
                RecordWeekRVAdapter.MyItemClickListener {
                override fun onItemClick(day: String) {
                    binding.recordDivisionView.visibility = View.VISIBLE
                    binding.recordEmptyTv.visibility= View.VISIBLE
                    binding.recordNotifyTv.visibility = View.GONE
                    recordService.getSingleResultList(userJwt,day)
                    recordService.getFolderResultList(userJwt,day)
                }
            })
        }
        recordService.recordCount(userJwt)
        //메인화면 폴더, 개별 리사이클러뷰 레이아웃 매니저
        binding.recordFolderresultRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recordResultRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        //화면 넓이
        val width  = getScreenSize(this)
        binding.recordWeekRecyclerview.addItemDecoration(HorizontalItemDecorator(width/42))
        //클릭 이벤트 함수
        clickevent()

        return binding.root
    }

    private fun clickevent() {
        //해당 월 text 클릭 이벤트(캘린터 액티비티로 넘어가기)
        binding.recordMonthTv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, CalendarActivity().apply {
                    arguments = Bundle().apply {
                    }
                })
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }
        //더보기 버튼 클릭 이벤트-> 블러뷰 나타내기
        binding.recordMoreIv.setOnClickListener {
            binding.recordBlurView.visibility = View.VISIBLE
            popupMenu()
        }
        //닫기 버튼 클릭 이벤트-> 블러뷰 닫기
        binding.recordCloseIv.setOnClickListener {
            binding.recordBlurView.visibility = View.GONE
            popupMenu()
        }
        //블러뷰 클릭 이벤트-> 블러뷰 닫기
        binding.recordBlurView.setOnClickListener {
            binding.recordBlurView.visibility = View.GONE
            popupMenu()
        }
    }

    private fun init() {
        //시작시 해당월, 날짜 (오늘날짜로 default)
        binding.recordDateTv.setText(setdate())
        binding.recordMonthTv.setText(setMonth())
    }

    //개별기록 프래그먼트 이동 함수
    private fun changeSingleRecordFragment(hasRecording:Boolean,recordIdx: Int,result:RandomResultListResult) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, SingleRecordFragment(hasRecording,recordIdx,result).apply {
                arguments = Bundle().apply {
                }
            })
            .addToBackStack(null)
            .commitAllowingStateLoss()

    }
    //폴더기록 프래그먼트 이동 함수
    private fun changeFolderRecordFragment(hasRecording: Boolean,folderIdx:Int, resultList:ArrayList<InFolderListResult>) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, FolderRecordFragment(hasRecording,folderIdx,resultList).apply {
                arguments = Bundle().apply {
                }
            })
            .addToBackStack(null)
            .commitAllowingStateLoss()

    }
    //개별, 폴더 기록조회 프래그먼트 이동 함수
    private fun changeRecordFragment(folderIdx: Int){
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, RecordLookFragment(folderIdx).apply {
                arguments = Bundle().apply {
                }
            })
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }
    //오늘 날짜 설정 함수
    private fun setdate(): String {
        val now: Long = System.currentTimeMillis()
        val date = Date(now)
        val dateFormat = SimpleDateFormat("yyyy년 MM월 dd일", Locale("ko", "KR"))
        val stringDate = dateFormat.format(date)

        return stringDate
    }
    //해당월 설정 함수
    private fun setMonth(): String {
        val now: Long = System.currentTimeMillis()
        val month = Date(now)
        val monthFormat = SimpleDateFormat("MM", Locale("ko", "KR"))
        val stringMonth = "< "+(monthFormat.format(month).toInt()).toString()+"월"

        return stringMonth
    }
    //화면크기 받아오는 함수
    fun getScreenSize(fragment : Fragment): Int {
        val display = requireActivity().windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x
        return width
    }
    //더보기 메뉴 애니메이션 함수
    private fun popupMenu() {

        var px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60f, this.resources.displayMetrics)

        if (add) {

            var writeAnimator = ObjectAnimator.ofFloat(binding.recordFolderplusIv, "translationY", 0f, -px)
            writeAnimator.duration = 400
            writeAnimator.interpolator = OvershootInterpolator()
            writeAnimator.target = binding.recordFolderplusIv
            writeAnimator.start()

            var photoAnimator = ObjectAnimator.ofFloat(binding.recordTrashIv, "translationY", 0f, -px*2)
            photoAnimator.duration = 500
            photoAnimator.interpolator = OvershootInterpolator()
            photoAnimator.target = binding.recordTrashIv
            photoAnimator.start()

            binding.recordMoreIv.visibility = View.GONE
            binding.recordCloseIv.visibility = View.VISIBLE
            add = !add
        } else {

            var writeAnimator = ObjectAnimator.ofFloat(binding.recordFolderplusIv, "translationY", -px, 0f)
            writeAnimator.duration = 400
            writeAnimator.interpolator = OvershootInterpolator()
            writeAnimator.target = binding.recordFolderplusIv
            writeAnimator.start()

            var photoAnimator = ObjectAnimator.ofFloat(binding.recordTrashIv, "translationY", -px*2, 0f)
            photoAnimator.duration = 500
            photoAnimator.interpolator = OvershootInterpolator()
            photoAnimator.target = binding.recordTrashIv
            photoAnimator.start()

            binding.recordMoreIv.visibility = View.VISIBLE
            binding.recordCloseIv.visibility = View.GONE

            add = !add
        }
    }

    override fun onRecordCountLoading() {
        binding.recordCountTv.text = "0"
    }

    override fun onRecordCountSuccess(result: Int) {
        binding.recordCountTv.text = result.toString()
    }

    override fun onRecordCountFailure(code: Int, message: String) {
        Toast.makeText(
            activity, message, Toast.LENGTH_SHORT
        ).show()
    }

    override fun onSingleResultListSuccess(result: ArrayList<SingleResultListResult>) {
        records = result
        val recordResultRVAdapter = RecordResultRVAdapter(records)
        binding.recordResultRecyclerview.adapter = recordResultRVAdapter

        if(recordResultRVAdapter.itemCount==0){
            binding.recordDivisionView.visibility = View.GONE
        }
        if(recordResultRVAdapter.itemCount>0){
            binding.recordNotifyTv.visibility = View.VISIBLE
            binding.recordEmptyTv.visibility = View.GONE
        }


        binding.recordFolderplusIv.setOnClickListener{
            DialogFolderSelect(records).show((context as MainActivity).supportFragmentManager,"dialog")
        }

        binding.recordTrashIv.setOnClickListener{
            DialogFolderDelete(records,folders).show((context as MainActivity).supportFragmentManager,"dialog")
        }

        recordResultRVAdapter.setMyItemClickListener(object :
            RecordResultRVAdapter.MyItemClickListener {
            override fun onItemClick(hasRecording: Boolean,recordIdx :Int,result:RandomResultListResult) {
                changeSingleRecordFragment(hasRecording,recordIdx,result)
            }
            override fun onItemView(randomResultIdx:Int) {
                changeRecordFragment(randomResultIdx)
            }
        })
    }

    override fun onSingleResultListFailure(code: Int, message: String) {
        val empty = ArrayList<SingleResultListResult>()
        val recordResultRVAdapter = RecordResultRVAdapter(empty)
        binding.recordResultRecyclerview.adapter = recordResultRVAdapter
        Log.e("기록하자개별메인api",message)
        binding.recordDivisionView.visibility = View.GONE
    }

    override fun onFolderResultListSuccess(result: ArrayList<FolderResultList>) {
        folders = result
        val recordFolderResultNameRVAdapter = RecordFolderResultNameRVAdapter(folders)
        binding.recordFolderresultRecyclerview.adapter = recordFolderResultNameRVAdapter

        if(recordFolderResultNameRVAdapter.itemCount==0){
            binding.recordDivisionView.visibility = View.GONE
        }

        if(recordFolderResultNameRVAdapter.itemCount>0){
            binding.recordNotifyTv.visibility = View.VISIBLE
            binding.recordEmptyTv.visibility = View.GONE
        }

        binding.recordTrashIv.setOnClickListener{
            DialogFolderDelete(records,folders).show((context as MainActivity).supportFragmentManager,"dialog")
        }

        recordFolderResultNameRVAdapter.setMyItemClickListener(object :
            RecordFolderResultNameRVAdapter.MyItemClickListener {
            override fun onItemClickPencil(hasRecording: Boolean,folderIdx:Int,resultList:ArrayList<InFolderListResult>) {
                changeFolderRecordFragment(hasRecording,folderIdx,resultList)
            }

            override fun onItemView(folderIdx:Int) {
                changeRecordFragment(folderIdx)
            }

            override fun onModifyClick(folder: FolderResultList) {
                DialogFolderModify(folder).show((context as MainActivity).supportFragmentManager,"dialog")
            }

            override fun onBreakUpClick(folderIdx: Int) {
                DialogRealBreakup(folderIdx).show((context as MainActivity).supportFragmentManager,"dialog")
            }
        })
    }

    override fun onFolderResultListFailure(code: Int, message: String) {
        val empty = ArrayList<FolderResultList>()
        val recordFolderResultNameRVAdapter = RecordFolderResultNameRVAdapter(empty)
        binding.recordFolderresultRecyclerview.adapter = recordFolderResultNameRVAdapter
        Log.e("기록하자폴더메인api",message)
        binding.recordDivisionView.visibility = View.GONE
    }
}

