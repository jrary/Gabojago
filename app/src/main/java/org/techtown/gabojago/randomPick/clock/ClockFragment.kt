package org.techtown.gabojago.randomPick.clock

import android.animation.Animator
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.rotationMatrix
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_clock.view.*
import org.techtown.gabojago.MainActivity
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.FragmentClockBinding
import org.techtown.gabojago.randomPick.HomeMenuFragment
import java.util.*

class ClockFragment : Fragment() {

    lateinit var binding: FragmentClockBinding
    var startNum: Int = 12
    var endNum: Int = 12
    var getResClock: Int = 12
    var clockAngle = arrayOf(
            30f,
            60f,
            90f,
            120f,
            150f,
            180f,
            210f,
            240f,
            270f,
            300f,
            330f,
            360f
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = FragmentClockBinding.inflate(layoutInflater)

        var getClockOption = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode ==  RESULT_OK){
                startNum = result.data?.getIntExtra("start", 12)!!
                endNum = result.data?.getIntExtra("end", 12)!!
            }
        }

        binding.clockBackBtn.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, HomeMenuFragment())
                    .commitAllowingStateLoss()
        }
        binding.clockOptionBtn.setOnClickListener {
            val intent = Intent(activity, ClockOptionActivity::class.java)
            getClockOption.launch(intent)
            activity?.overridePendingTransition(R.anim.anim_up, R.anim.anim_none)
        }
        binding.clockGoBtn.setOnClickListener {
            moveClock()
            binding.clockOptionBtn.visibility = View.GONE
            binding.clockGoBtn.visibility = View.GONE
            binding.clockInfoTitleTv.visibility = View.INVISIBLE
            binding.clockInfoTv.visibility = View.INVISIBLE
            Handler().postDelayed({
                binding.clockResultTv.text = getResClock.toString() + "시 방향"
                binding.clockRetryBtn.visibility = View.VISIBLE
                binding.clockSaveBtn.visibility = View.VISIBLE
                binding.clockResultTv.visibility = View.VISIBLE
            }, 1500)
        }
        binding.clockRetryBtn.setOnClickListener {
            binding.clockOptionBtn.visibility = View.VISIBLE
            binding.clockGoBtn.visibility = View.VISIBLE
            binding.clockInfoTitleTv.visibility = View.VISIBLE
            binding.clockInfoTv.visibility = View.VISIBLE
            binding.clockRetryBtn.visibility = View.GONE
            binding.clockSaveBtn.visibility = View.GONE
            binding.clockResultTv.visibility = View.GONE
            val rotateAnimationResult = RotateAnimation(0F, 0F, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            rotateAnimationResult.fillAfter = true
            binding.clockArrowIv.startAnimation(rotateAnimationResult)
        }
        binding.clockSaveBtn.setOnClickListener{
            Toast.makeText(
                    context, "뽑기 결과가 저장됐어!", Toast.LENGTH_SHORT
            ).show()
        }
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        binding.clockInfoTitleTv.visibility = View.GONE
        binding.clockInfoTv.visibility = View.GONE
        val animationOpen = AnimationUtils.loadAnimation(activity, R.anim.anim_open_scale)
        binding.clockMainView.startAnimation(animationOpen)
    }

    override fun onResume() {
        super.onResume()
        val animationClose = AnimationUtils.loadAnimation(activity, R.anim.anim_close_scale)
        binding.clockMainView.startAnimation(animationClose)
        binding.clockInfoTitleTv.visibility = View.VISIBLE
        binding.clockInfoTv.visibility = View.VISIBLE
    }

    private fun moveClock(){
        var startAngle: Float
        var endAngle: Float
        var resAngle: Float
        if(startNum > endNum){
            getResClock = getClockResult(startNum, endNum + 12)
            startAngle = -360f + clockAngle[startNum - 1]
            endAngle = clockAngle[endNum - 1]
            if (getResClock <= 12){
                resAngle = -360f + clockAngle[getResClock - 1]
            }
            else{
                resAngle = clockAngle[getResClock - 1]
            }
        }
        else{
            getResClock = getClockResult(startNum + 12, endNum)
            startAngle = clockAngle[getResClock - 1]
            endAngle = clockAngle[getResClock - 1]
            resAngle = clockAngle[getResClock - 1]
        }
        Handler().postDelayed({
            val rotateAnimation = RotateAnimation(0f, startAngle, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            rotateAnimation.duration = 300
            rotateAnimation.fillAfter = true
            //   rotateAnimation.interpolator =
            binding.clockArrowIv.startAnimation(rotateAnimation)
        }, 100)
        Handler().postDelayed({
            val rotateAnimation = RotateAnimation(startAngle, endAngle, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            rotateAnimation.duration = 400
            rotateAnimation.fillAfter = true
            //   rotateAnimation.interpolator =
            binding.clockArrowIv.startAnimation(rotateAnimation)
        }, 600)
        Handler().postDelayed({
            val rotateAnimationResult = RotateAnimation(endAngle, resAngle, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            rotateAnimationResult.fillAfter = true
            rotateAnimationResult.duration = 300
            //   rotateAnimation.interpolator =
            binding.clockArrowIv.startAnimation(rotateAnimationResult)
        }, 1000)
    }
    private fun getClockResult(start: Int, end: Int): Int{
        val random = Random()
        val res = (start + random.nextInt(end - start)) % 12
        Log.d("clockResultRandomFunc", res.toString())
        return res
    }
}