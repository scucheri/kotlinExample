package com.example.bytedance.myapplication

class User constructor(lastName: String){
        //Kotlin的类可以有属性。 属性可以用关键字var 声明为可变的，否则使用只读关键字val。

        var lastName: String = lastName
            get() = field.toUpperCase()   // 将变量赋值后转换为大写
            set

        var no: Int = 100
            get() = field                // 后端变量
            set(value) {
                if (value < 10) {       // 如果传入的值小于 10 返回该值
                    field = value
                } else {
                    field = -1         // 如果传入的值大于等于 10 返回 -1
                }
            }

        var heiht: Float = 145.4f
            private set

        override fun toString(): String {
            return "lastName: ${lastName}"
        }
    }