package com.chow.arch.concurrent.base.sync003;

/**
 * Created by shelvin chow on 2017/5/6.
 */
public class MyObject
{
    public synchronized void method1()
    {
        System.out.println(Thread.currentThread().getName());
        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void method2()
    {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args)
    {
        final MyObject myObject = new MyObject();

        Thread thread1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                myObject.method1();
            }
        }, "t1");

        Thread thread2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                myObject.method2();
            }
        }, "t2");

        thread1.start();
        thread2.start();
    }
}
