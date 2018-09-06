package com.example.bytedance.myapplication

interface Factory<T> {
    fun create(): T
}

class MyClass1 {
    companion object : Factory<MyClass1> {
        override fun create(): MyClass1 = MyClass1()
    }

    fun test(){
        println("myclass factory")
    }
}