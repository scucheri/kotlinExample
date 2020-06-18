package com.example.bytedance.myapplication.finaltoanonymousinnerclass;


public class AnonymousDemo1
{
    public static String TAG = "AnonymousDemo1";

    public static void main(String args[])
    {
        new AnonymousDemo1().play();
    }
 
    private void play()
    {
        Dog dog = new Dog();
        Runnable runnable = new Runnable()
        {
            public void run()
            {
                while(dog.getAge()<100)
                {
                    // 过生日，年龄加一
                    dog.happyBirthday();
                    dog.setAge(80);
                    dog = new Dog();

                    // 打印年龄
                   System.out.println("AnonymousDemo1 runnable age is " + dog.getAge());
                }
            }
        };
        new Thread(runnable).start();

        Runnable runnable1 = new Runnable()
        {
            public void run()
            {
                while(dog.getAge()<100)
                {
                    // 过生日，年龄加一
                    dog.happyBirthday();
                    // 打印年龄
                    System.out.println("AnonymousDemo1 runnable1 age is " + dog.getAge());
                }
            }
        };
        new Thread(runnable1).start();

    }

    private class Dog{
        private int age = 1;

        private void happyBirthday(){
            age ++;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}