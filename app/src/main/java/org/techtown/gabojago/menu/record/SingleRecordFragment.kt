package org.techtown.gabojago.menu.record

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.techtown.gabojago.databinding.FragmentSinglerecordBinding

import com.google.android.material.bottomnavigation.BottomNavigationView
import org.techtown.gabojago.main.MainActivity
import org.techtown.gabojago.R
import org.techtown.gabojago.main.getJwt
import org.techtown.gabojago.menu.record.look.RecordLookRVAdapter
import org.techtown.gabojago.menu.record.recordRetrofit.*
import java.util.ArrayList


class SingleRecordFragment(private  val recordIdx:Int,private val result:RandomResultListResult) : Fragment() ,SingleRecordingView, SingleLookView, SingleModifyView{
    lateinit var binding: FragmentSinglerecordBinding
    val url= mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSinglerecordBinding.inflate(inflater, container, false)

        val recordService = RecordService()
        recordService.setSingleLookView(this@SingleRecordFragment)

        val userJwt = getJwt(requireContext(), "userJwt")

        recordService.getSingleLook(userJwt, recordIdx)

        val recordPictureRVAdapter = RecordPictureRVAdapter()
        binding.singleRecordPictureRecyclerview.adapter = recordPictureRVAdapter

        binding.singleRecordResultTv.text = result.randomResultContent
        when (result.randomResultType) {
            1 -> {
                binding.singleRecordTitleIv.setImageResource(R.drawable.dolimpan)
                binding.singleRecordCircleIv.setImageResource(R.drawable.resultimage_dolimpan_orange)
            }2 -> {
                binding.singleRecordTitleIv.setImageResource(R.drawable.nsibanghiang)
                binding.singleRecordCircleIv.setImageResource(R.drawable.resultimage_nsibang_orange)
            }3 -> {
                binding.singleRecordTitleIv.setImageResource(R.drawable.colorbox)
                binding.singleRecordCircleIv.setImageResource(R.drawable.resultimage_japangi_orange)
            }4 -> {
                binding.singleRecordTitleIv.setImageResource(R.drawable.binglebingle)
                binding.singleRecordCircleIv.setImageResource(R.drawable.resultimage_random_orange)
            }
        }



        clickevent()
        init()

        return binding.root
    }

    private fun clickevent(){
        binding.singleRecordBackarrow.setOnClickListener{
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

    override fun onSingleRecordingSuccess() {
        Log.e("개별기록","성공")
    }

    override fun onSingleRecordingFailure(code: Int, message: String) {
        Toast.makeText(
            activity, message, Toast.LENGTH_SHORT
        ).show()
    }

    override fun onSingleLookSuccess(result: SingleLookResult) {
        try {
            binding.singleRecordTitleEt.setText(result.eachContentResult.recordingTitle)
            binding.singleRecordStarscore.rating = result.eachContentResult.recordingStar.toFloat()
            binding.singleRecordWriteEt.setText(result.eachContentResult.recordingContent)

            binding.singleRecordCompleteView.setOnClickListener {
                val recordService = RecordService()
                recordService.setSingleModifyView(this@SingleRecordFragment)
                val userJwt = getJwt(requireContext(), "userJwt")
                recordService.putSingleModify(userJwt, SingleModifyRequest(
                    binding.singleRecordStarscore.rating.toDouble(),
                    binding.singleRecordWriteEt.text.toString(),
                    binding.singleRecordTitleEt.text.toString(),
                ),recordIdx)

                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, RecordFragment().apply {
                        arguments = Bundle().apply {
                        }
                    })
                    .addToBackStack(null)
                    .commitAllowingStateLoss()
            }

            binding.singleRecordCompleteTv.setOnClickListener {
                val recordService = RecordService()
                recordService.setSingleModifyView(this@SingleRecordFragment)
                val userJwt = getJwt(requireContext(), "userJwt")
                recordService.putSingleModify(userJwt, SingleModifyRequest(
                    binding.singleRecordStarscore.rating.toDouble(),
                    binding.singleRecordWriteEt.text.toString(),
                    binding.singleRecordTitleEt.text.toString(),
                ),recordIdx)

                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, RecordFragment().apply {
                        arguments = Bundle().apply {
                        }
                    })
                    .addToBackStack(null)
                    .commitAllowingStateLoss()
            }
        } catch (e: NullPointerException) {
            binding.singleRecordTitleEt.setText("제목을 입력해줘!")
            binding.singleRecordStarscore.rating = 3.5F
            binding.singleRecordWriteEt.setText("내용을 입력해줘!")

            binding.singleRecordCompleteView.setOnClickListener {
                val recordService = RecordService()
                recordService.setSingleRecordingView(this@SingleRecordFragment)
                val userJwt = getJwt(requireContext(), "userJwt")
                recordService.putSingleRecord(userJwt, recordIdx, SingleRecordingRequest(
                    binding.singleRecordStarscore.rating.toDouble(),
                    binding.singleRecordWriteEt.text.toString(),
                    binding.singleRecordTitleEt.text.toString(),
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

            binding.singleRecordCompleteTv.setOnClickListener {
                val recordService = RecordService()
                recordService.setSingleRecordingView(this@SingleRecordFragment)
                val userJwt = getJwt(requireContext(), "userJwt")
                recordService.putSingleRecord(userJwt, recordIdx, SingleRecordingRequest(
                    binding.singleRecordStarscore.rating.toDouble(),
                    binding.singleRecordWriteEt.text.toString(),
                    binding.singleRecordTitleEt.text.toString(),
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

        }
    }

    override fun onSingleLookFailure(code: Int, message: String) {
        Toast.makeText(
            activity, message, Toast.LENGTH_SHORT
        ).show()
    }

    override fun onSingleModifySuccess() {
        Log.e("개별기록","성공")
    }

    override fun onSingleModifyFailure(code: Int, message: String) {
        Toast.makeText(
            activity, message, Toast.LENGTH_SHORT
        ).show()
    }

}