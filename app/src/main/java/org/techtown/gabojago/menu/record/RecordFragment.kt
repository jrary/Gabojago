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
import android.util.TypedValue
import android.view.*
import android.view.animation.OvershootInterpolator
import org.techtown.gabojago.Record.DialogFolderMake
import org.techtown.gabojago.record.RecordResultRVAdapter
import org.techtown.gabojago.record.RecordWeekRVAdapter
import org.techtown.gabojago.record.SingleRecordFragment



class RecordFragment : Fragment() {

    lateinit var binding: FragmentRecordBinding

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

        val recordResultRVAdapter = RecordResultRVAdapter()
        binding.recordResultRecyclerview.adapter = recordResultRVAdapter


        val recordFolderResultNameRVAdapter = RecordFolderResultNameRVAdapter()
        binding.recordFolderresultRecyclerview.adapter = recordFolderResultNameRVAdapter

        val width  = getScreenSize(this)
        binding.recordWeekRecyclerview.addItemDecoration(HorizontalItemDecorator(width/42))

        recordResultRVAdapter.setMyItemClickListener(object :
            RecordResultRVAdapter.MyItemClickListener {
            override fun onItemClick() {
                changeSingleRecordFragment()
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
            DialogFolderSelect().show((context as MainActivity).supportFragmentManager,"dialog")
        }

    }

    private fun init() {
        binding.recordDateTv.setText(setdate())
        binding.recordMonthTv.setText(setMonth())
    }

    private fun changeSingleRecordFragment() {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, SingleRecordFragment().apply {
                arguments = Bundle().apply {
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

