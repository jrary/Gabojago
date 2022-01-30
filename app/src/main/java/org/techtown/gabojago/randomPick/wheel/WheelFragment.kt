package org.techtown.gabojago.randomPick.wheel

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import org.json.JSONArray
import org.techtown.gabojago.MainActivity
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.FragmentWheelBinding
import org.techtown.gabojago.randomPick.HomeMenuFragment
import org.techtown.gabojago.randomPick.clock.ClockOptionActivity

class WheelFragment : Fragment() {
    lateinit var binding: FragmentWheelBinding
    var optionList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWheelBinding.inflate(layoutInflater)

        var wheelArr = arrayOf(
            binding.wheel02,
            binding.wheel03,
            binding.wheel04,
            binding.wheel05,
            binding.wheel06,
        )

        var getWheelOptionArray = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == Activity.RESULT_OK){
                optionList = result.data?.getStringArrayListExtra("wheel")!!
                setWheel(wheelArr)
            }
        }

        binding.wheelOptionBtn.setOnClickListener{
            val intent = Intent(activity, WheelOptionActivity::class.java)
            intent.putExtra("wheel", optionList)
            getWheelOptionArray.launch(intent)
            activity?.overridePendingTransition(R.anim.anim_up, R.anim.anim_none)
        }
        binding.wheelBackBtn.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, HomeMenuFragment())
                    .commitAllowingStateLoss()
        }
        binding.wheelGoBtn.setOnClickListener {
            Toast.makeText(
                context, "뽑기 결과가 저장됐어!", Toast.LENGTH_SHORT
            ).show()
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        optionList.add("옵션 1")
        optionList.add("옵션 2")
    }

    override fun onPause() {
        super.onPause()
        binding.wheelInfoTitleTv.visibility = View.GONE
        binding.wheelInfoTv.visibility = View.GONE
        val animationOpen = AnimationUtils.loadAnimation(activity, R.anim.anim_open_scale)
        binding.wheelMainView.startAnimation(animationOpen)
    }

    override fun onResume() {
        super.onResume()
        val animationClose = AnimationUtils.loadAnimation(activity, R.anim.anim_close_scale)
        binding.wheelMainView.startAnimation(animationClose)
        binding.wheelInfoTitleTv.visibility = View.VISIBLE
        binding.wheelInfoTv.visibility = View.VISIBLE
    }
    private fun setWheel(wheelArr: Array<AppCompatImageView>){
        for(i: Int in 0..4){
            wheelArr[i].visibility = View.GONE
        }
        wheelArr[optionList.size - 2].visibility = View.VISIBLE
    }
}