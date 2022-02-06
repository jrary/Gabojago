package org.techtown.gabojago.menu.record

import HorizontalItemDecorator
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.techtown.gabojago.MainActivity
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.FragmentRecordBinding
import java.text.SimpleDateFormat
import java.util.*

import android.graphics.Point
import android.provider.Settings.Global.putString
import android.util.TypedValue
import android.view.*
import android.view.animation.OvershootInterpolator
import com.google.gson.Gson
import org.techtown.gabojago.data.SingleRecord


class RecordFragment : Fragment() {

    lateinit var binding: FragmentRecordBinding

    var records= ArrayList<SingleRecord>()

    var add: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRecordBinding.inflate(inflater, container, false)


        binding.recordFolderresultRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding.recordResultRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding.recordWeekRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val recordWeekRVAdapter = RecordWeekRVAdapter()
        binding.recordWeekRecyclerview.adapter = recordWeekRVAdapter

        val recordResultRVAdapter = RecordResultRVAdapter(records)
        binding.recordResultRecyclerview.adapter = recordResultRVAdapter


        val recordFolderResultNameRVAdapter = RecordFolderResultNameRVAdapter()
        binding.recordFolderresultRecyclerview.adapter = recordFolderResultNameRVAdapter

        val width  = getScreenSize(this)
        binding.recordWeekRecyclerview.addItemDecoration(HorizontalItemDecorator(width/42))

        recordResultRVAdapter.setMyItemClickListener(object :
            RecordResultRVAdapter.MyItemClickListener {
            override fun onItemClick(record :SingleRecord) {
                changeSingleRecordFragment(record)
            }
        })

        recordFolderResultNameRVAdapter.setMyItemClickListener(object :
            RecordFolderResultNameRVAdapter.MyItemClickListener {
            override fun onItemClick() {
                changeFolderRecordFragment()
            }

            override fun onItemView() {

            }
        })


        clickevent()
        init()
        initData(records)

        return binding.root
    }

    private fun clickevent() {
        binding.recordMonthTv.setOnClickListener {
            startActivity(Intent(activity, CalendarActivity::class.java))
        }

        binding.recordMoreIv.setOnClickListener {
            binding.recordBlurView.visibility = View.VISIBLE
            popupMenu()
        }

        binding.recordCloseIv.setOnClickListener {
            binding.recordBlurView.visibility = View.GONE
            popupMenu()
        }

        binding.recordBlurView.setOnClickListener {
            binding.recordBlurView.visibility = View.GONE
            popupMenu()
        }

        binding.recordFolderplusIv.setOnClickListener{
            DialogFolderSelect(records).show((context as MainActivity).supportFragmentManager,"dialog")
            val gson = Gson()
            val recordJson = gson.toJson(records)
            arguments?.putString("recordList", recordJson)
        }

    }

    private fun init() {
        binding.recordDateTv.setText(setdate())
        binding.recordMonthTv.setText(setMonth())
    }

    private fun initData(records : ArrayList<SingleRecord>){
        records.add(
            SingleRecord(
                0,
                "제목없음",
                "버스",
                R.drawable.resultimage_dolimpan_gray,
                false,
                R.drawable.dolimpan
            ))
        records.add(
            SingleRecord(
                1,
                "제목없음",
                "4,7",
                R.drawable.resultimage_random_gray,
                false,
                R.drawable.binglebingle
            ))
        records.add(
            SingleRecord(
                2,
                "제목없음",
                "9시 방향",
                R.drawable.resultimage_nsibang,
                false,
                R.drawable.nsibanghiang
            ))
        records.add(
            SingleRecord(
                3,
                "제목없음",
                "9시 방향",
                R.drawable.resultimage_japangi,
                false,
                R.drawable.nsibanghiang
            ))
    }

    private fun changeSingleRecordFragment(record: SingleRecord) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, SingleRecordFragment().apply {
                arguments = Bundle().apply {
                    val gson = Gson()
                    val recordJson = gson.toJson(record)
                    putString("record", recordJson)
                }
            })
            .addToBackStack(null)
            .commitAllowingStateLoss()

    }

    private fun changeFolderRecordFragment() {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, FolderRecordFragment().apply {
                arguments = Bundle().apply {
                }
            })
            .addToBackStack(null)
            .commitAllowingStateLoss()

    }

    private fun setdate(): String {
        val now: Long = System.currentTimeMillis()
        val date = Date(now)
        val dateFormat = SimpleDateFormat("yyyy년MM월dd일", Locale("ko", "KR"))
        val stringDate = dateFormat.format(date)

        return stringDate
    }

    private fun setMonth(): String {
        val now: Long = System.currentTimeMillis()
        val month = Date(now)
        val monthFormat = SimpleDateFormat("< MM월", Locale("ko", "KR"))
        val stringMonth = monthFormat.format(month)

        return stringMonth
    }

    fun getScreenSize(fragment : Fragment): Int {
        val display = requireActivity().windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x
        return width
    }

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

}

