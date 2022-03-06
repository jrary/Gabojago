@file:Suppress("DEPRECATION")
package org.techtown.gabojago.main

import android.content.Context
import android.content.res.Resources
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import org.techtown.gabojago.R
import org.techtown.gabojago.databinding.ToastBoardWhiteBinding

object MyToast {

    fun createToast(context: Context, message: String): Toast? {

        val binding: ToastBoardWhiteBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.toast_board_white, null, false)

        binding.toastBoardTv.text = message

        return Toast(context).apply {
            setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 90.toPx())
            duration = Toast.LENGTH_SHORT
            view = binding.root
        }
    }

    private fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
}