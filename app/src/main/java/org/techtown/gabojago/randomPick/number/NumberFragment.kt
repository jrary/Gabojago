package org.techtown.gabojago.randomPick.number

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.techtown.gabojago.MainActivity
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.FragmentNumberBinding
import org.techtown.gabojago.randomPick.HomeMenuFragment
import java.util.*

class NumberFragment : Fragment() {
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
                Log.d("GETRESARRAY", resArray.toString())
            }
        }

        binding.numberBackBtn.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeMenuFragment())
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

        return binding.root
    }

    private fun getNumbers(): Array<Int?> {
        var resNumbers = arrayOfNulls<Int>(num)
        val random = Random()
        val bound = endNum - startNum + 1
        for(i: Int in 0 until num){
            val res = startNum + random.nextInt(bound)
            resNumbers[i] = res
        }
        return resNumbers
    }
}