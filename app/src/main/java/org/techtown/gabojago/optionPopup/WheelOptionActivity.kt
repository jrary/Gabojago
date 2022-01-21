package org.techtown.gabojago.optionPopup

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.techtown.gabojago.databinding.ActivityWheelOptionBinding

class WheelOptionActivity : AppCompatActivity() {

    lateinit var binding: ActivityWheelOptionBinding
    private val optionList = ArrayList<WheelOptionData>()
    var totalProb = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWheelOptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        val rvAdapter = WheelOptionRVAdapter(optionList)

        optionList.apply{
            add(WheelOptionData("옵션 1", 1, 20))
            add(WheelOptionData("옵션 2", 1, 20))
            add(WheelOptionData("옵션 3", 1, 20))
            add(WheelOptionData("옵션 4", 1, 20))
            add(WheelOptionData("옵션 5", 1, 20))

            rvAdapter.optionList = optionList
            rvAdapter.notifyDataSetChanged()
        }

        rvAdapter.setMyItemClickListener(object : WheelOptionRVAdapter.MyItemClickListener {
            override fun onItemClick() {
                openSelectActivity()
            }
        })

        binding.wheelOptionTv.setOnClickListener {
            totalProb += 1
            optionList.add(WheelOptionData("옵션" + (optionList.size + 1), 1, totalProb/(optionList.size + 1)*100))
        }

        binding.wheelComp2Btn.setOnClickListener {
            startActivity(Intent(this@WheelOptionActivity, WheelSelectActivity::class.java))
        }

        binding.wheelCompBtn.setOnClickListener {
            finish()
        }
    }

    private fun openSelectActivity() {
        startActivity(Intent(this@WheelOptionActivity, WheelSelectActivity::class.java))
    }
}