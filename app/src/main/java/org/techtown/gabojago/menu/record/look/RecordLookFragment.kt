package org.techtown.gabojago.menu.record.look

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import org.techtown.gabojago.main.MainActivity
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.FragmentRecordLookBinding
import org.techtown.gabojago.menu.record.RecordFolderResultRVAdapter
import org.techtown.gabojago.menu.record.RecordFragment
import java.util.*

class RecordLookFragment: Fragment() {
    lateinit var binding: FragmentRecordLookBinding
    private val MIN_SCALE = 0.85f
    private val MIN_ALPHA = 0.5f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecordLookBinding.inflate(inflater, container, false)

        var imageArr = getImageList()
        binding.recordLookPictureVp.adapter = RecordLookViewpagerAdapter(imageArr)
        binding.recordLookPictureVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.recordLookPictureVp.setPageTransformer(ZoomOutPageTransformer())
        binding.recordLookCircleIndicator.setViewPager2(binding.recordLookPictureVp)

        val resultList = getRandomResultList()

        val recordLookRVAdapter = RecordLookRVAdapter(resultList)
        binding.recordResultRecyclerview.adapter = recordLookRVAdapter

        binding.recordLookBackBtn.setOnClickListener{
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

    private fun getImageList(): ArrayList<Int>{

//        for(i in 0..LookImageList.size - 1){
//            imageArr.add(LookImageList[i])
//        }
        return arrayListOf<Int>(R.drawable.record_look_example_img, R.drawable.record_look_example_img, R.drawable.record_look_example_img)
    }

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

    private fun getRandomResultList(): ArrayList<recordLook>{
        var resultList = ArrayList<recordLook>()

        resultList.add(recordLook("12:34", "돌려돌려 돌림판", "횡단보도"))
        resultList.add(recordLook("12:34", "돌려돌려 돌림판", "횡단보도"))
        resultList.add(recordLook("12:34", "돌려돌려 돌림판", "횡단보도"))
        resultList.add(recordLook("12:34", "돌려돌려 돌림판", "횡단보도"))
        resultList.add(recordLook("12:34", "돌려돌려 돌림판", "횡단보도"))
        resultList.add(recordLook("12:34", "돌려돌려 돌림판", "횡단보도"))
        resultList.add(recordLook("12:34", "돌려돌려 돌림판", "횡단보도"))

        return resultList
    }
}