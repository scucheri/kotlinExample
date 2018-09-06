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
            testKotlin()
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

    fun testKotlin(){
        var testInt : Int
        testInt = 3

        var name: String?= null   //类型后面加 ？表示该变量可以为空

        var teststr :String = "testa"

        try {
            teststr = name!!   // !! 表示不合理时抛出异常，比如testStr默认不能为空，name 为空时，跑出空指针异常
        }catch(e: Exception){
            e.printStackTrace()
        } finally {
            println("print testStr len1 "+name?.length)   //变量调用前加 ？，表示先判断这个变量是否为空，如果变量为空，则直接返回null

            println("print testStr len is ${teststr.length}")
        }

        val text = """
             |First Line
             |Second Line
             \|Third Line
             """.trimMargin()   //换行使用 |
        println("text: ${text}")


        var xt = 10
        val text1 = if (xt> 5)
            "xt > 5"
        else "xt <= 5"
        println("text1: ${text1}")


        var a1: Int = -2
        var b:Int = 10
        val andResult  = a1 and b
        val orResult   = a1 or b
        val xorResult  = a1 xor b
        val rightShift = a1 shr 2
        val leftShift  = a1 shl 2   //val 表示final变量

//        shl(bits) – 有符号左移 (Java 的 <<)
//        shr(bits) – 有符号右移 (Java 的 >>)
//        ushr(bits) – 无符号右移 (Java 的 >>>)
//        and(bits) – 位与
//        or(bits) – 位或
//        xor(bits) – 位异或
//        inv() – 位非

        var x : Int = 8
        var other = "320"
        if (x is Int) {
            println("x is Int: ${x}")

        }
        val text3 = other as String
        if (x in 0..10) {  // if x>=0 && x<=10
            println("x: ${x}")
        }


        var a : String = "dbfrhrb"
        if (a is String) {
            val result = a.substring(1)
            println(result)
            println("result: ${result}")
        }

        val x1 = 20// value
        val xResult = when (x) {
            0, 11 -> "0 or 11"
            in 1..10 -> "from 1 to 10"
            !in 12..14 -> "not from 12 to 14"
            else -> if (isOdd(x1)) { "is odd" } else { "otherwise" }
        }
        println("xResult: ${xResult}")

        val y1 = 10// value
        val yResult = when {
            isNegative(y1) -> "is Negative"
            isZero(y1) -> "is Zero"
            isOdd(y1) -> "is odd"
            else -> "otherwise"
        }
        println("yResult: ${yResult}")


        for (i in 1 until 11) {
            println("i: ${i}")
        }

        for (i in 1..10 step 2) {
            println("i: ${i}")
        }


        val numbers = listOf(1, 2, 3,6,4,9)
        for (item in numbers) {
            println("item: ${item}")
        }

        for ((index, item) in numbers.withIndex()) {
            println("item: ${item};  index: ${index}")
        }

        numbers.forEach {
            println(it)
        }

        numbers.filter  { it > 5 }
                .forEach { println(it) }


        val map = mapOf(1 to "One",
                2 to "Two",
                3 to "Three")
        for ((key, value) in map) {
            println("key: ${key};  value: ${value}")
        }


        val groups = numbers.groupBy {
            if (it and 1 == 0) "even" else "odd"  //这个 and 是 bitwise 按位的and
        }
        println("groups: ${groups}")
        println("groups: ${groups.size}")


        val (evens, odds) = numbers.partition { it and 1 == 0 }
        println("evens: ${evens}")
        println("odds: ${odds}")

        var users = getUsers()
        users = users.sortedBy { it.lastName }
        println("users: ${users}")


        var tx: Int = 10
        while (tx > 0) {
            tx--
            println("tx:${tx}")
        }

        var ty: Int = 10
        do {
            ty ++
            println("ty:${ty}")

        } while (ty < 20)


    }

    fun getUsers(): List<User> {
        return listOf(User("xiao"),User("yu"),User("mi"),User("xiao1"))  //新建对象不需要 new
    }


    fun isNegative(d : Int) : Boolean{
        if(d > 0){
            return true
        }
        return false
    }

    fun isZero(d: Int) : Boolean{
        return d == 0
    }

    fun isOdd(d : Int) : Boolean{
        return d%2 ==1
    }


    fun callJava(source: List<Int>) {
        val list = ArrayList<Int>()
        // “for”-循环用于 Java 集合：
        for (item in source) {
            list.add(item)
        }
        // 操作符约定同样有效：
        for (i in 0..source.size - 1) {
            list[i] = source[i] // 调用 get 和 set
        }
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
