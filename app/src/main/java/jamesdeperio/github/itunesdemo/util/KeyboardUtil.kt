package jamesdeperio.github.itunesdemo.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

object KeyboardUtil {
    fun hideSoftKeyboard(view: View, activity: AppCompatActivity) {
        val imm: InputMethodManager? = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}