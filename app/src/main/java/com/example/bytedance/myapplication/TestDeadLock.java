package com.example.bytedance.myapplication;

import java.util.concurrent.TimeUnit;

public class TestDeadLock {
     private static final String TAG ="TestDeadLock ";

    public static class LeftObject {
         
    }
     
    public static class RightObject {
         
    }
    private Object leftLock = new LeftObject();
    private Object rightLock = new RightObject();
     
    public void leftRight() {
        synchronized (leftLock) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(TAG + Thread.currentThread().getName() + " leftRight, in leftLock,  wait for rightLock ");
            synchronized (rightLock) {
                System.out.println(TAG + Thread.currentThread().getName()+ " leftRight");
            }
        }
    }
    public void rightLeft() {
        synchronized (rightLock) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(TAG + Thread.currentThread().getName() + " rightLeft, in rightLock , wait for leftLock ");
            synchronized (leftLock) {
                System.out.println(TAG + Thread.currentThread().getName()+ " rightLeft");
            }
        }
    }
    public static void main(String[] args) {
        final TestDeadLock test = new TestDeadLock();
        System.out.println(TAG + Thread.currentThread().getName() + " main");

        new Thread(new Runnable() {
             
            @Override
            public void run() {
                System.out.println(TAG + Thread.currentThread().getName() + " leftRight start");
                test.leftRight();
            }
        }).start();
         
        new Thread(new Runnable() {
             
            @Override
            public void run() {
                System.out.println(TAG + Thread.currentThread().getName() + " rightLeft start");
                test.rightLeft();
            }
        }).start();
    }
}