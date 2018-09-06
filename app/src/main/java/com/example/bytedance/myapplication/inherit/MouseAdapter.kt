package com.example.bytedance.myapplication.inherit

open class MouseAdapter {
    open var adapter : Int = 100

    open fun test(){
        println("MouseAdapter: ${adapter} ")
    }
}