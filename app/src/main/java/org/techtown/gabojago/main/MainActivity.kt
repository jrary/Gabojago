package org.techtown.gabojago.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import org.techtown.gabojago.R
import org.techtown.gabojago.menu.record.RecordFragment
import org.techtown.gabojago.databinding.ActivityMainBinding
import org.techtown.gabojago.menu.manage.system.ManageFragment
import org.techtown.gabojago.menu.home.contents.HomeFragment



class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        initNavigation()
    }
    fun changeFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.main_frm,RecordFragment())
            .commitAllowingStateLoss()
        Log.e("프래그먼트변경","성공")
    }

    private fun initNavigation() {

        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()
        binding.mainBnv.selectedItemId = R.id.homeFragment

        binding.mainBnv.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.recordFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, RecordFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.manageFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, ManageFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

            }
            false
        }
    }
}
