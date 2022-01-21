package org.techtown.gabojago.randomPick

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import org.json.JSONArray
import org.techtown.gabojago.randomPick.wheel.WheelOptionActivity
import org.techtown.gabojago.databinding.FragmentWheelBinding
import org.techtown.gabojago.optionPopup.WheelOptionData


class WheelFragment : Fragment() {
    private var gson: Gson = Gson()
    lateinit var binding: FragmentWheelBinding
    private var isOpen: Boolean = true
    var optionList = ArrayList<WheelOptionData>()
    var totalProb = 5

    fun isOpened(): Boolean{
        return isOpen
    }
    fun changeIsOpened() {
        isOpen = !isOpened()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWheelBinding.inflate(layoutInflater)

        binding.wheelOptionBtn.setOnClickListener{
            Log.d("fragment - ITEMNUM", optionList.size.toString())
            startActivity(Intent(activity, WheelOptionActivity::class.java))
        }

        return binding.root
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