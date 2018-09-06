package com.example.bytedance.myapplication;


public class SingletonKt1 private constructor() {
    init { println("This ($this) is a singleton kt1") }

    companion object {  //object 实现单例
        val instance = SingletonKt1()  // 表示 public static final
    }
    var b:String? = null

    fun test(){
        println("kotlin SingletonKt1 test ")

    }
}
