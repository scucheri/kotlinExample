package com.example.bytedance.myapplication;

public class SingletonJava {
    private static SingletonJava instance;

    public static SingletonJava getInstance(){
        if(instance == null){
            instance = new SingletonJava();
        }
        return instance;
    }

    public void test(){
        System.out.println("java singleton test");

        SingletonKt1.Companion.getInstance().test(); //
    }

}
