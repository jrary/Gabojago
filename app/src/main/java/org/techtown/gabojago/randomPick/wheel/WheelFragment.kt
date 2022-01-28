package org.techtown.gabojago.randomPick.wheel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import org.json.JSONArray
import org.techtown.gabojago.MainActivity
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.FragmentWheelBinding
import org.techtown.gabojago.optionPopup.WheelOptionData
import org.techtown.gabojago.randomPick.HomeMenuFragment


class WheelFragment : Fragment() {
    private var gson: Gson = Gson()
    lateinit var binding: FragmentWheelBinding
    var optionList = ArrayList<WheelOptionData>()
    var totalProb = 5

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWheelBinding.inflate(layoutInflater)

        binding.wheelOptionBtn.setOnClickListener{
            Log.d("fragment - ITEMNUM", optionList.size.toString())
            startActivity(Intent(activity, WheelOptionActivity::class.java))
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

    //여기서부터는 그냥... 저장소
    fun storeJson(){
        val jsArray = JSONArray(optionList)
        val spf = activity?.getSharedPreferences("wheelOptions", AppCompatActivity.MODE_PRIVATE)
        val editor = spf!!.edit()
//
//        editor.remove("wheelOptions")
//        editor.apply()

        editor.putString("wheelOptions", jsArray.toString())
        editor.apply()
    }

    fun getJson(){
        val jsArray = JSONArray(optionList)
        val spf = activity?.getSharedPreferences("wheelOptions", AppCompatActivity.MODE_PRIVATE)

        //optionList = gson.fromJson(spf, WheelOptionData::class.java)
    }
}