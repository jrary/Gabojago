package org.techtown.gabojago.menu.randomPick.clock

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import org.techtown.gabojago.main.MainActivity
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.FragmentClockBinding
import org.techtown.gabojago.main.getJwt
import org.techtown.gabojago.menu.randomPick.home.HomeMenuFragment
import org.techtown.gabojago.menu.randomPick.home.RandomService
import org.techtown.gabojago.menu.randomPick.home.RandomView
import java.util.*

class ClockFragment : Fragment(), RandomView {

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

        var viewArr = arrayOf(
            binding.clock1,
            binding.clock2,
            binding.clock3,
            binding.clock4,
            binding.clock5,
            binding.clock6,
            binding.clock7,
            binding.clock8,
            binding.clock9,
            binding.clock10,
            binding.clock11,
            binding.clock12
        )

        var getClockOption = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode ==  RESULT_OK){
                startNum = result.data?.getIntExtra("start", 12)!!
                endNum = result.data?.getIntExtra("end", 12)!!
                if(startNum > endNum){
                    clockRangeSetter(viewArr, startNum - 1, endNum + 12 - 1)
                }
                else{
                    clockRangeSetter(viewArr, startNum - 1, endNum - 1)
                }
            }
        }

        binding.clockBackBtn.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, HomeMenuFragment())
                    .commitAllowingStateLoss()
        }
        binding.clockOptionBtn.setOnClickListener {
            val intent = Intent(activity, ClockOptionActivity::class.java)
            intent.putExtra("start", startNum)
            intent.putExtra("end", endNum)
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
            }, 4700)
        }
        binding.clockRetryBtn.setOnClickListener {
            val rotateAnimationResult = RotateAnimation(0F, 0F, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            rotateAnimationResult.fillAfter = true
            binding.clockArrowIv.startAnimation(rotateAnimationResult)
            binding.clockResultTv.visibility = View.GONE
            binding.clockRetryBtn.visibility = View.GONE
            binding.clockSaveBtn.visibility = View.GONE
            Handler().postDelayed({
                moveClock()
            }, 200)
            Handler().postDelayed({
                binding.clockResultTv.text = getResClock.toString() + "시 방향"
                binding.clockRetryBtn.visibility = View.VISIBLE
                binding.clockSaveBtn.visibility = View.VISIBLE
                binding.clockResultTv.visibility = View.VISIBLE
            }, 4900)
        }
        binding.clockSaveBtn.setOnClickListener{
            if(getResClock == -1){
                Toast.makeText(
                    activity, "No value", Toast.LENGTH_SHORT
                ).show()
            }
            else{
                val randomService = RandomService()
                randomService.setRandomView(this@ClockFragment)

                val userJwt = getJwt(requireContext(), "userJwt")
                randomService.storeResult(userJwt, getResClock.toString() + "시 방향", "B")
            }
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

    //startNum, endNum의 범위는 1~12이다.
    //index의 범위는 0~11이다
    //getResClock으로 넘겨주는 값은 0~11이고, getResClock으로 리턴받는 값은 1~12이다.
    //clockRangeSetter로 보내는 값의 범위는 0~11이다
    private fun moveClock(){
        var startAngle: Float
        var endAngle: Float
        var resAngle: Float
        if(startNum > endNum){
            getResClock = getClockResult(startNum - 1, endNum + 12 - 1)
            startAngle = -360f + clockAngle[startNum - 1]
            endAngle = clockAngle[endNum - 1]
            if (getResClock <= 12){
                resAngle = -360f + clockAngle[getResClock - 1]
            }
            else{
                getResClock %= 12
                resAngle = clockAngle[getResClock - 1]
            }
        //    clockRangeSetter(viewArr, startNum - 1, endNum + 12 - 1)
        }
        else{
            getResClock = getClockResult(startNum - 1, endNum - 1)
            startAngle = clockAngle[startNum - 1]
            endAngle = clockAngle[endNum - 1]
            resAngle = clockAngle[getResClock - 1]
       //     clockRangeSetter(viewArr, startNum - 1, endNum - 1)
        }
        clockAnimation(startAngle, endAngle, resAngle)
    }
    private fun getClockResult(start: Int, end: Int): Int{
        val random = Random()
        val bound = end - start + 1
        val res = start + random.nextInt(bound)
        return res + 1
    }

    private fun clockAnimation(startAngle: Float, endAngle: Float, resAngle: Float){
        Handler().postDelayed({
            val rotateAnimation = RotateAnimation(0f, startAngle, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            rotateAnimation.duration = 100
            rotateAnimation.fillAfter = true
            //   rotateAnimation.interpolator =
            binding.clockArrowIv.startAnimation(rotateAnimation)
        }, 100)
        Handler().postDelayed({
            val rotateAnimation = RotateAnimation(startAngle, endAngle, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            rotateAnimation.duration = 300
            rotateAnimation.fillAfter = true
            //   rotateAnimation.interpolator =
            binding.clockArrowIv.startAnimation(rotateAnimation)
        }, 400)
        Handler().postDelayed({
            val rotateAnimation = RotateAnimation(endAngle, startAngle, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            rotateAnimation.duration = 300
            rotateAnimation.fillAfter = true
            //   rotateAnimation.interpolator =
            binding.clockArrowIv.startAnimation(rotateAnimation)
        }, 700)
        Handler().postDelayed({
            val rotateAnimation = RotateAnimation(startAngle, endAngle, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            rotateAnimation.duration = 500
            rotateAnimation.fillAfter = true
            //   rotateAnimation.interpolator =
            binding.clockArrowIv.startAnimation(rotateAnimation)
        }, 1000)
        Handler().postDelayed({
            val rotateAnimation = RotateAnimation(endAngle, startAngle, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            rotateAnimation.duration = 500
            rotateAnimation.fillAfter = true
            //   rotateAnimation.interpolator =
            binding.clockArrowIv.startAnimation(rotateAnimation)
        }, 1500)
        Handler().postDelayed({
            val rotateAnimation = RotateAnimation(startAngle, endAngle, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            rotateAnimation.duration = 800
            rotateAnimation.fillAfter = true
            //   rotateAnimation.interpolator =
            binding.clockArrowIv.startAnimation(rotateAnimation)
        }, 2000)
        Handler().postDelayed({
            val rotateAnimation = RotateAnimation(endAngle, startAngle, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            rotateAnimation.duration = 800
            rotateAnimation.fillAfter = true
            //   rotateAnimation.interpolator =
            binding.clockArrowIv.startAnimation(rotateAnimation)
        }, 2800)
        Handler().postDelayed({
            val rotateAnimationResult = RotateAnimation(startAngle, resAngle, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            rotateAnimationResult.fillAfter = true
            rotateAnimationResult.duration = 900
            //   rotateAnimation.interpolator =
            binding.clockArrowIv.startAnimation(rotateAnimationResult)
        }, 3600)
    }
    private fun clockRangeSetter(viewArr: Array<TextView>, start: Int, end: Int){
        viewArr[start].setTextColor(Color.rgb(255,103,69))
        viewArr[end % 12].setTextColor(Color.rgb(255,103,69))
        for(i: Int in start..end){
            viewArr[i % 12].setTextColor(Color.rgb(255,147,124))
        }
    }

    override fun onRandomResultSuccess() {
        Toast.makeText(
            context, "뽑기 결과가 저장됐어!", Toast.LENGTH_SHORT
        ).show()
    }

    override fun onRandomResultFailure(code: Int, message: String) {
        Toast.makeText(
            activity, message, Toast.LENGTH_SHORT
        ).show()
    }
}