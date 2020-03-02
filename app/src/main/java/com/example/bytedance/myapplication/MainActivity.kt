package com.example.bytedance.myapplication

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.bytedance.myapplication.inherit.MouseAdapterFather
import com.example.bytedance.myapplication.interf.kotlinInterface0
import com.example.bytedance.myapplication.interf.kotlinInterface1
import com.example.bytedance.myapplication.singleton.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

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
//            testWaitAsyncTask(it)
            testVolatile()
        }

    }

    fun testWaitAsyncTask(v: View) {
        val lock = java.lang.Object()
        val waitTask = WaitTask(lock)
        synchronized(lock) {
            try {
                waitTask.execute("test--WaitNotify--Task")
                // Wait for this worker thread’s notification
                Log.i("test_WaitTask: ", "before wait  ${Thread.currentThread().name}")
                lock.wait()
                Log.i("test_WaitTask: ", "after wait  ${Thread.currentThread().name}")
            } catch (e: InterruptedException) {
            }
        }
    }

    internal class WaitTask(private val lock: java.lang.Object) : AsyncTask<String, Int, Long>() {
        override fun doInBackground(vararg params: String): Long {
            synchronized(lock) {
                Log.i("test_WaitTask: ", "wait task start:  $params ${Thread.currentThread().name}")
                // Finished, notify the main thread

                Thread.sleep(5000)
                Log.i("test_WaitTask: ", "before notify  ${Thread.currentThread().name}")
                lock.notify()
                Log.i("test_WaitTask: ", "after notify  ${Thread.currentThread().name}")
            }
            return 0
        }
    }


    @Volatile private var running = false

    fun testVolatile(){
        start()
        Thread.sleep(2000)
        stop()
    }

    fun start() {
        running = true
        thread(start = true) {
            while (running) { // 另一个线程stop之后这个线程能立即感知到
                println("test_volatile Still running: ${Thread.currentThread()}")
            }
        }
    }

    fun stop() {
        running = false
        println("test_volatile  Stopped: ${Thread.currentThread()}")
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
