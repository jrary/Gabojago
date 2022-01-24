package org.techtown.gabojago.randomPick.wheel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.ActivityWheelOptionBinding
import org.techtown.gabojago.optionPopup.WheelOptionData
import org.techtown.gabojago.optionPopup.WheelSelectActivity
import java.util.ArrayList

class WheelOptionActivity : AppCompatActivity() {

    lateinit var binding: ActivityWheelOptionBinding
    var optionList = ArrayList<WheelOptionData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWheelOptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val wheelFragment = WheelFragment()
        val rvAdapter = WheelOptionRVAdapter(wheelFragment.optionList)
        Log.d("start - ITEMNUM", wheelFragment.optionList.size.toString())

        rvAdapter.setMyItemClickListener(object : WheelOptionRVAdapter.MyItemClickListener {
            override fun onItemClick() {
                openSelectActivity()
            }
        })

        binding.recordResultRecyclerview.adapter = rvAdapter
        binding.recordResultRecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.wheelOptionTv.setOnClickListener {
            wheelFragment.totalProb += 1
            rvAdapter.addItem(wheelFragment.totalProb/(wheelFragment.optionList.size + 1)*100)
        }

        binding.wheelCompBtn.setOnClickListener {
            Log.d("compBtn - ITEMNUM", wheelFragment.optionList.size.toString())
            finish()
            overridePendingTransition(R.anim.anim_down, R.anim.anim_none)
        }
    }

    private fun openSelectActivity() {
        startActivity(Intent(this@WheelOptionActivity, WheelSelectActivity::class.java))
    }
}