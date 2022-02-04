package org.techtown.gabojago

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import org.techtown.gabojago.menu.record.RecordFragment
import org.techtown.gabojago.databinding.ActivityMainBinding
import org.techtown.gabojago.menu.goAgain.GoagainFragment
import org.techtown.gabojago.menu.manage.ManageFragment
import org.techtown.gabojago.menu.randomPick.home.HomeFragment



class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        initNavigation()
    }

    private fun initNavigation() {
        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        binding.mainBnv.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    binding.mainBnv.setBackgroundResource(R.drawable.bottom_back1)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.recordFragment -> {
                    binding.mainBnv.setBackgroundResource(R.drawable.bottom_back2)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, RecordFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.goagainFragment -> {
                    binding.mainBnv.setBackgroundResource(R.drawable.bottom_back3)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, GoagainFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.manageFragment -> {
                    binding.mainBnv.setBackgroundResource(R.drawable.bottom_back4)
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
