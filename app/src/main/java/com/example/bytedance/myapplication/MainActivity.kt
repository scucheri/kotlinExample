package com.example.bytedance.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
        }
    }

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
