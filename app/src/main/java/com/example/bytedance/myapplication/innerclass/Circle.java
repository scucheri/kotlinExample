package com.example.bytedance.myapplication.innerclass;

/**
 * Created by xiaoxiaoyu on 2020-06-18.
 */
public class Circle {
    double radius = 0;

    public Circle(double radius) {
        this.radius = radius;
    }

    class Draw {     //内部类
        public void drawSahpe() {
            System.out.println("drawshape");
        }
    }
}
