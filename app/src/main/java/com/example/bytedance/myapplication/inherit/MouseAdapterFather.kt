
package com.example.bytedance.myapplication.inherit
    class MouseAdapterFather : MouseAdapter(){  //object 实现类的继承
        override var adapter: Int = 120

        override fun test(){
           super.test()
           println("MouseAdapterFather: ${adapter}")
           }
    }