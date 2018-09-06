package com.example.bytedance.myapplication.singleton

//package可以没有

class SingletonKt0 private constructor() {
    init { println("This ($this) is a singleton kt0") }

     private object Holder {  //object 实现单例
        val INSTANCE = SingletonKt0()  //私有的final static变量
    }

    companion object {
        val instance: SingletonKt0 by lazy { Holder.INSTANCE }

    }
    var b:String? = null

     fun test(){
        println("kotlin com.example.bytedance.myapplication.singleton.SingletonKt0 test ")
    }
}
