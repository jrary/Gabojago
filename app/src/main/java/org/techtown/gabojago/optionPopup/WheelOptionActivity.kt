package org.techtown.gabojago.optionPopup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import org.techtown.gabojago.R
import org.techtown.gabojago.Record.CalendarActivity
import org.techtown.gabojago.databinding.ActivityWheelOptionBinding

class WheelOptionActivity : AppCompatActivity() {
    init{
        instance = this
    }
    companion object{
        lateinit var instance: WheelOptionActivity
        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }
    lateinit var binding: ActivityWheelOptionBinding
    var totalProb = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWheelOptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR


        val optionList = ArrayList<WheelOptionData>()
        binding.recordResultRecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val wheelOptionRecyclerViewAdapter = WheelOptionRVAdapter(optionList, this)

       // wheelOptionRecyclerViewAdapter.setMyItemClickListener()

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
}