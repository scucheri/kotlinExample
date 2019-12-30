package com.example.bytedance.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bytedance.myapplication.inherit.MouseAdapterFather
import com.example.bytedance.myapplication.interf.kotlinInterface0
import com.example.bytedance.myapplication.interf.kotlinInterface1
import com.example.bytedance.myapplication.singleton.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example of a call to a native method
        sample_text.text = stringFromJNI()

        testButton.text = "testNew"

        testButton.setOnClickListener {
            println("onclick "+it.x )
            GrammarTest.testKotlin()
            testSingleton()
            testInterface()
            testInherit()
            MyClass1.create().test()
            testFunctionParam()
        }

    }

    private fun  testFunctionParam() {
        val list = listOf("1uuf", "2iejnfg", "sfhbbgghg3")

        Log.i("testFunctionParam max", this.max(list, { a, b -> compare(a, b) }))

        Log.i("testFunctionParam 1", calc(3, 9, { a, b -> Operator().add(a, b) }).toString())
        Log.i("testFunctionParam 2", calc(1, { Operator().inc(it) }).toString())
    }


    fun compare(a: String, b: String) : Boolean = a.length < b.length

    fun <T> max(collection: Collection<out T>, less: (T, T) -> Boolean): T? {
        var max: T? = null
        for (it in collection)
            if (max == null || less(max!!, it))
                max = it
        return max
    }


    class Operator {
        fun add(a: Int, b: Int) = a + b
        fun inc(a: Int) = a + 1
    }

    fun calc(a: Int, b: Int, opr: (Int, Int) -> Int) = opr(a, b)
    fun calc(a: Int, opr: (Int) -> Int) = opr(a)



    fun testInherit(){
        var fatherAdapter  = MouseAdapterFather()
        fatherAdapter.test()
    }


    fun testInterface(){
        var testInterface = object: kotlinInterface0, kotlinInterface1 {  //object 实现匿名内部类
            override fun test1() {
                println("kotlinInterface0")
            }

            override fun test2(a: Int) {
                println("kotlinInterface0: ${a}")
            }

            override fun code2(a: String) {
                println("kotlinInterface1： ${a}")
            }

            override fun code1() {
                println("kotlinInterface1")
            }
        }
        testInterface.code1()
        testInterface.code2("xiaoxiaoyu")
        testInterface.test1()
        testInterface.test2(12)
    }


    fun testSingleton(){
        SingletonKt1.instance.test()
        SingletonKt0.instance.test()
        SingletonKt2.registerDataProvider()
        SingletonJava.getInstance().test()
        SingletonJava0.getInstance().test() //由java文件生成的kotlin文件
    }

    override  fun onResume() {
        super.onResume()
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
