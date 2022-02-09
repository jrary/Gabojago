package org.techtown.gabojago.menu.record.look

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import org.techtown.gabojago.main.MainActivity
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.FragmentRecordLookBinding
import org.techtown.gabojago.menu.record.RecordFragment
import java.util.*

class RecordLookFragment: Fragment() {
    lateinit var binding: FragmentRecordLookBinding
    private val MIN_SCALE = 0.95f
    private val MIN_ALPHA = 0.5f
    var isOpened: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecordLookBinding.inflate(inflater, container, false)

        //Set Viewpager and Indicator
        var imageArr = getImageList()
        binding.recordLookPictureVp.adapter = RecordLookViewpagerAdapter(imageArr)
        binding.recordLookPictureVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.recordLookPictureVp.setPageTransformer(ZoomOutPageTransformer())
        binding.recordLookCircleIndicator.setViewPager2(binding.recordLookPictureVp)

        //Set Rating star
        setStarState(2.5)

        //Set RecyclerView
        val resultList = getRandomResultList()
        val recordLookRVAdapter = RecordLookRVAdapter(resultList)
        binding.recordResultRecyclerview.adapter = recordLookRVAdapter

        //Open RecyclerView Event
        binding.recordLookView.setOnClickListener {
            randomIsOpened(isOpened)
        }

        //Go to the Previous page
        binding.recordLookBackBtn.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, RecordFragment().apply {
                    arguments = Bundle().apply {
                    }
                })
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }
        return binding.root
    }

    //Animation on the ViewPager
    inner class ZoomOutPageTransformer : ViewPager2.PageTransformer {
        override fun transformPage(view: View, position: Float) {
            view.apply {
                val pageWidth = width
                val pageHeight = height
                when {
                    position < -1 -> { // [-Infinity,-1)
                        // This page is way off-screen to the left.
                        alpha = 0f
                    }
                    position <= 1 -> { // [-1,1]
                        // Modify the default slide transition to shrink the page as well
                        val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                        val vertMargin = pageHeight * (1 - scaleFactor) / 2
                        val horzMargin = pageWidth * (1 - scaleFactor) / 2
                        translationX = if (position < 0) {
                            horzMargin - vertMargin / 2
                        } else {
                            horzMargin + vertMargin / 2
                        }

                        // Scale the page down (between MIN_SCALE and 1)
                        scaleX = scaleFactor
                        scaleY = scaleFactor

                        // Fade the page relative to its size.
                        alpha = (MIN_ALPHA +
                                (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                    }
                    else -> { // (1,+Infinity]
                        // This page is way off-screen to the right.
                        alpha = 0f
                    }
                }
            }
        }
    }

    //Set the state of RecyclerView
    private fun randomIsOpened(toOpen: Boolean) {
        if (toOpen) {
            binding.recordLookArrowBtn.setImageResource(R.drawable.result_look_arrow_clicked)
            binding.recordResultRecyclerview.visibility = View.VISIBLE
        } else {
            binding.recordLookArrowBtn.setImageResource(R.drawable.result_look_arrow)
            binding.recordResultRecyclerview.visibility = View.GONE
        }
        isOpened = !isOpened
    }

    //Function to store the Image in the ViewPager
    private fun getImageList(): ArrayList<Int> {

        var imageList = ArrayList<Int>()

        imageList.add(R.drawable.record_look_example_img)
        imageList.add(R.drawable.record_look_example_img)
        imageList.add(R.drawable.record_look_example_img)
        imageList.add(R.drawable.record_look_example_img)
        imageList.add(R.drawable.record_look_example_img)
        imageList.add(R.drawable.record_look_example_img)
        imageList.add(R.drawable.record_look_example_img)
        imageList.add(R.drawable.record_look_example_img)
        imageList.add(R.drawable.record_look_example_img)

        return imageList
    }

    //Function to store the Random value in the RecyclerView
    private fun getRandomResultList(): ArrayList<RecordLook> {
        var resultList = ArrayList<RecordLook>()

        resultList.add(RecordLook("12:34", "돌려돌려 돌림판", "횡단보도"))
        resultList.add(RecordLook("12:34", "N시 방향", "9시 방향"))
        resultList.add(RecordLook("12:34", "색깔 뽑기", "검은색 계열"))
        resultList.add(RecordLook("12:34", "숫자 뽑기", "2,1,10,7"))
        resultList.add(RecordLook("12:34", "돌려돌려 돌림판", "버스"))
        resultList.add(RecordLook("12:34", "N시 방향", "2시 방향"))
        resultList.add(RecordLook("12:34", "색깔 뽑기", "노란색 계열"))
        resultList.add(RecordLook("12:34", "숫자 뽑기", "8,17,3,2,9,15"))

        return resultList
    }

    //Function to set the star rate
    private fun setStarState(star: Double) {
        var starArr = arrayOf(
            binding.recordLookStar01LeftIv,
            binding.recordLookStar01RightIv,
            binding.recordLookStar02LeftIv,
            binding.recordLookStar02RightIv,
            binding.recordLookStar03LeftIv,
            binding.recordLookStar03RightIv,
            binding.recordLookStar04LeftIv,
            binding.recordLookStar04RightIv,
            binding.recordLookStar05LeftIv,
            binding.recordLookStar05RightIv
        )

        for(i in 0..(star*2).toInt() - 1){
            starArr[i].visibility = View.VISIBLE
        }
    }
}