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
import java.util.ArrayList

class WheelOptionActivity : AppCompatActivity() {

    lateinit var binding: ActivityWheelOptionBinding
    var res: String = "0"
    var curPosition : Int = 0
    private val wheelFragment = WheelFragment()
    private val rvAdapter = WheelOptionRVAdapter(wheelFragment.optionList)
    var optionList = ArrayList<WheelOptionData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWheelOptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        rvAdapter.setMyItemClickListener(object : WheelOptionRVAdapter.MyItemClickListener {
            override fun onItemClick(position: Int) {
                curPosition = position
                openSelectActivity()
            }
        })

        //Setting RecyclerView
        binding.recordResultRecyclerview.adapter = rvAdapter
        binding.recordResultRecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //Add the Item
        binding.wheelPlusBtn.setOnClickListener {
            wheelFragment.totalProb += 1
            rvAdapter.addItem(100/wheelFragment.totalProb)
        }
        //Finish
        binding.wheelCompBtn.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.anim_down, R.anim.anim_none)
        }
    }

    private fun openSelectActivity() {
        startActivityForResult(Intent(this@WheelOptionActivity, WheelSelectActivity::class.java), 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == 100){
            res = data?.getStringExtra("clock")!!
            Log.d("onActivityResult -", res)
            rvAdapter.updateRecordSize(curPosition, res.toInt())
        }
    }
}