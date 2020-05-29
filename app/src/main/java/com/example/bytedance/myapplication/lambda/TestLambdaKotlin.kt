package com.example.bytedance.myapplication.lambda

import android.util.Log
import java.util.Arrays

/**
 * Created by xiaoxiaoyu on 2020-05-29.
 */
object TestLambdaKotlin {
    private val TAG : String = "TestLambdaKotlin"

    fun filter(f: (Int) -> Boolean, integerList: List<Int>) {
        for (i in integerList) {
            if (f(i)) {
                Log.i(TAG, "$i")
            }
        }
    }

    fun lambdaDemo() {
        filter({ x -> x % 2 == 1 }, Arrays.asList(1, 2, 3, 4, 5, 6, 7))
    }

}