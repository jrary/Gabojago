package org.techtown.gabojago.menu.randomPick.number

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import org.techtown.gabojago.main.MainActivity
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.FragmentNumberBinding
import org.techtown.gabojago.main.getJwt
import org.techtown.gabojago.menu.randomPick.home.HomeMenuFragment
import org.techtown.gabojago.menu.randomPick.home.RandomService
import org.techtown.gabojago.menu.randomPick.home.RandomView
import java.util.*

class NumberFragment : Fragment(), RandomView {
    lateinit var binding: FragmentNumberBinding
    var startNum: Int = 0
    var endNum: Int = 0
    var num: Int = 0
    var isOverlap: Boolean = false
    lateinit var resArray: Array<Int?>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = FragmentNumberBinding.inflate(layoutInflater)

        var getNumberOption = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == AppCompatActivity.RESULT_OK){
                startNum = result.data?.getIntExtra("start", 0)!!
                endNum = result.data?.getIntExtra("end", 0)!!
                num = result.data?.getIntExtra("num", 0)!!
                isOverlap = result.data?.getBooleanExtra("overlap", true)!!
                resArray = getNumbers()
                Log.d("GETNUMBEROPTION", startNum.toString()+" "+ endNum.toString()+" "+num.toString()+" "+isOverlap.toString())
                for(i: Int in 0 until num){
                    Log.d("GETRESARRAY", resArray[i].toString())
                }
            }
        }

        var ballGroundArr = arrayOf(
            binding.numberBall01,
            binding.numberBall02,
            binding.numberBall03,
            binding.numberBall04,
            binding.numberBall05,
            binding.numberBall06,
            binding.numberBall07,
            binding.numberBall08,
            binding.numberBall09,
            binding.numberBall10
        )
        var resTextArr = arrayOf(
            binding.numberResult01Tv,
            binding.numberResult02Tv,
            binding.numberResult03Tv,
            binding.numberResult04Tv,
            binding.numberResult05Tv,
            binding.numberResult06Tv,
            binding.numberResult07Tv,
            binding.numberResult08Tv,
            binding.numberResult09Tv,
            binding.numberResult10Tv
        )

        binding.numberBackBtn.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeMenuFragment().apply {
                    arguments = Bundle().apply {
                    }
                })
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }

        binding.numberOptionBtn.setOnClickListener {
            val intent = Intent(activity, NumberOptionActivity::class.java)
            intent.putExtra("start", startNum)
            intent.putExtra("end", endNum)
            intent.putExtra("num", num)
            intent.putExtra("overlap", isOverlap)
            getNumberOption.launch(intent)
            activity?.overridePendingTransition(R.anim.anim_up, R.anim.anim_none)
        }

        binding.numberGoBtn.setOnClickListener {
            Handler().postDelayed({
                binding.numberOptionBtn.visibility = View.GONE
                binding.numberGoBtn.visibility = View.GONE
            }, 50)
            Handler().postDelayed({
                val animAlphaStart = AnimationUtils.loadAnimation(activity, R.anim.anim_alpha_start)
                binding.numberGroundIv.visibility = View.VISIBLE
                binding.numberGroundIv.startAnimation(animAlphaStart)
            }, 50)
            Handler().postDelayed({
                showAnimation(ballGroundArr, resTextArr)
            }, 300)
        }

        binding.numberRetryBtn.setOnClickListener {
            val animAlphaStart = AnimationUtils.loadAnimation(activity, R.anim.anim_alpha_start_longer)
            binding.numberResultView.visibility = View.GONE
            binding.numberContentsView.visibility = View.VISIBLE
            binding.numberContentsView.startAnimation(animAlphaStart)
        }

        binding.numberSaveBtn.setOnClickListener {
            if(resArray.isEmpty()){
                Toast.makeText(
                    context, "No value", Toast.LENGTH_SHORT
                ).show()
            }
            else{
                val randomService = RandomService()
                randomService.setRandomView(this@NumberFragment)

                var numberResString = ""
                for(i in 0..resArray.size - 1){
                    numberResString = numberResString + " " + resArray[i].toString()
                }

                Log.d("NUMBERRESULT", numberResString)

                val userJwt = getJwt(requireContext(), "userJwt")
                randomService.storeResult(userJwt, numberResString, "D")
            }
        }

        return binding.root
    }
    //Get the RESULT NUMBER -> RESARRAY
    private fun getNumbers(): Array<Int?> {
        var resNumbers = arrayOfNulls<Int>(num)
        val random = Random()
        val bound = endNum - startNum + 1
        if(isOverlap){
            for(i: Int in 0 until num){
                val res = startNum + random.nextInt(bound)
                resNumbers[i] = res
            }
        }
        else{
            var i = 0
            while(i < num){
                val res = startNum + random.nextInt(bound)
                resNumbers[i] = res
                for(j: Int in 0 until i){
                    if(resNumbers[j] == resNumbers[i]){
                        i--
                    }
                }
                i++
            }
        }

        return resNumbers
    }
    private fun showAnimation(ballArr: Array<AppCompatImageView>, resTextArr: Array<AppCompatTextView>){
        val dropBall = AnimationUtils.loadAnimation(activity, R.anim.anim_ball_drop)
        val resetBall = AnimationUtils.loadAnimation(activity, R.anim.anim_ball_reset)
        val animAlphaStart = AnimationUtils.loadAnimation(activity, R.anim.anim_alpha_start_longer)
        for(i in 0..resArray.size - 1){
            //Main Animation
            Handler().postDelayed({
                binding.numberDropBallIv.visibility = View.VISIBLE
                binding.numberDropBallIv.startAnimation(animAlphaStart)
            }, 200 + 1800 * i.toLong())
            Handler().postDelayed({
                binding.numberDropBallIv.startAnimation(dropBall) //400ms
            }, 1000 + 1800 * i.toLong())
            Handler().postDelayed({
                binding.numberDropBallIv.visibility = View.GONE
                binding.numberDropBallIv.startAnimation(resetBall)
                ballArr[i].visibility = View.VISIBLE
                ballArr[i].startAnimation(animAlphaStart) //500ms
            }, 1300 + 1800 * i.toLong())
        }
        Handler().postDelayed({
            resAnimation(resTextArr)
        }, (1000 + 1800 * resArray.size).toLong())
    }
    private fun resAnimation(resTextArr: Array<AppCompatTextView>){
        val animAlphaStart = AnimationUtils.loadAnimation(activity, R.anim.anim_alpha_start_longer)
        binding.numberContentsView.visibility = View.GONE
        binding.numberResultView.visibility = View.VISIBLE
        binding.numberResultView.startAnimation(animAlphaStart)
        for(i in 0..resArray.size - 1){
            Handler().postDelayed({
                resTextArr[i].text = resArray[i].toString()
                resTextArr[i].visibility = View.VISIBLE
                resTextArr[i].startAnimation(animAlphaStart) //200ms
            }, 700 * i.toLong())
        }
        Handler().postDelayed({
            binding.numberRetryBtn.visibility = View.VISIBLE
            binding.numberSaveBtn.visibility = View.VISIBLE
        }, 300 + 700 * resArray.size.toLong())
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

    override fun onPause() {
        super.onPause()
        val animationOpen = AnimationUtils.loadAnimation(activity, R.anim.anim_open_scale)
        binding.numberMainIv.startAnimation(animationOpen)
    }

    override fun onResume() {
        super.onResume()
        val animationClose = AnimationUtils.loadAnimation(activity, R.anim.anim_close_scale)
        binding.numberMainIv.startAnimation(animationClose)
    }
}