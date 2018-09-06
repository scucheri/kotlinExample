package com.example.bytedance.myapplication

class SingletonJava0 {

    fun test() {
        println("java singleton test")
    }

    companion object {
         private var instance: SingletonJava0? = null //默认是public，如果是private需要特意指定

        fun getInstance(): SingletonJava0 {
            if (instance == null) {
                instance = SingletonJava0()
            }
            return instance as SingletonJava0
        }
    }

}
