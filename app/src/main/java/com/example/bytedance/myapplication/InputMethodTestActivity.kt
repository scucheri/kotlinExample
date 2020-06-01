package com.example.bytedance.myapplication

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.example.bytedance.myapplication.leaktest.InputMethodLeakUtil
import kotlinx.android.synthetic.main.activity_input_method_test.input_method_et

/**
 * Created by xiaoxiaoyu on 2020-05-29.
 */
class InputMethodTestActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_method_test)
    }

    override fun onPostResume() {
        InputMethodLeakUtil.fixInputMethodManagerLeak(this)
        super.onPostResume()
        input_method_et.postDelayed(Runnable {

            input_method_et.setFocusable(true)
            input_method_et.setFocusableInTouchMode(true)
            input_method_et.requestFocus()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.showSoftInput(input_method_et, 0)

            InputMethodLeakUtil.fixInputMethodManagerLeak(this)


        }, 500)
    }
}