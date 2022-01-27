package org.techtown.gabojago.randomPick.clock

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_clock.view.*
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.FragmentClockBinding

class ClockFragment : Fragment() {

    lateinit var binding: FragmentClockBinding
    var startNum: Int = 12
    var endNum: Int = 12

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

        binding.clockOptionBtn.setOnClickListener {
            val intent = Intent(activity, ClockOptionActivity::class.java)
            getClockOption.launch(intent)
            activity?.overridePendingTransition(R.anim.anim_up, R.anim.anim_none)
        }
        binding.clockGoBtn.setOnClickListener {
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
        checkState()
    }

    private fun checkState(){
        Toast.makeText(
                context, startNum.toString() +" , "+ endNum.toString(), Toast.LENGTH_SHORT
        ).show()
    }
}