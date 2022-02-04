package org.techtown.gabojago

import android.content.Context
import android.graphics.ColorSpace
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
<<<<<<< Updated upstream:app/src/main/java/org/techtown/gabojago/main/MainActivity.kt
import org.techtown.gabojago.menu.record.RecordFragment
import org.techtown.gabojago.databinding.ActivityMainBinding
import org.techtown.gabojago.menu.goAgain.GoagainFragment
import org.techtown.gabojago.menu.manage.ManageFragment
import org.techtown.gabojago.menu.randomPick.home.HomeFragment
=======
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.simform.custombottomnavigation.Model
import org.techtown.gabojago.Record.RecordFragment
import org.techtown.gabojago.databinding.ActivityMainBinding
import androidx.navigation.fragment.NavHostFragment.findNavController as navHostFragmentFindNavController
>>>>>>> Stashed changes:app/src/main/java/org/techtown/gabojago/MainActivity.kt

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        setBottomNavigationWithNavController(savedInstanceState)
    }

    private fun setBottomNavigationWithNavController(savedInstanceState: Bundle?) {

        val activeIndex = savedInstanceState?.getInt("activeIndex") ?: 0

        val navController = findNavController(R.id.main_frm)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.recordFragment,
                R.id.goagainFragment,
                R.id.manageFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        val menuItems = arrayOf(
            Model(
                icon = R.drawable.go,                // Icon
                destinationId = R.id.homeFragment,     // destinationID
                id = 0,               // ID // Icon with Text, If you don't want text then don't pass it
            // notification count if you want to show then pass specific count else pass R.string.empty_value or don't pass anything
            ),
            Model(
                icon = R.drawable.memo_pencil_bottom,
                destinationId = R.id.recordFragment,
                id = 1
                // notification count not needed here so, not passed
            ),
            Model(
                R.drawable.bookmark,
                R.id.goagainFragment,
                2
            ),
            Model(
                R.drawable.person,
                R.id.manageFragment,
                3
            )
        )

        binding.mainBottomNavigation.apply {
            setMenuItems(menuItems, activeIndex)
            setupWithNavController(navController)
        }

    }

//    private fun initNavigation() {
//        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment())
//            .commitAllowingStateLoss()
//
//        binding.mainBnv.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.homeFragment -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.main_frm, HomeFragment())
//                        .commitAllowingStateLoss()
//                    return@setOnItemSelectedListener true
//                }
//
//                R.id.recordFragment -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.main_frm, RecordFragment())
//                        .commitAllowingStateLoss()
//                    return@setOnItemSelectedListener true
//                }
//
//                R.id.goagainFragment -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.main_frm, GoagainFragment())
//                        .commitAllowingStateLoss()
//                    return@setOnItemSelectedListener true
//                }
//
//                R.id.manageFragment -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.main_frm, ManageFragment())
//                        .commitAllowingStateLoss()
//                    return@setOnItemSelectedListener true
//                }
//
//            }
//            false
//        }
//    }
}
