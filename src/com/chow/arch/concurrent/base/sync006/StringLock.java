package com.chow.arch.concurrent.base.sync006;

/**
 * Created by shelvin chow on 2017/5/11.
 */
public class StringLock
{
    public void method()
    {
        synchronized (new String("字符串常量"))
        {
            while (true)
            {
                System.out.println("current thread " + Thread.currentThread().getName() + " enters.");
                try
                {
                    Thread.sleep(1000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                System.out.println("current thread " + Thread.currentThread().getName() + " ends.");
            }
        }
    }

    public static void main(String[] args)
    {
        final StringLock stringLock = new StringLock();

        Thread thread1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                stringLock.method();
            }
        }, "t1");

        Thread thread2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                stringLock.method();
            }
        }, "t2");

        thread1.start();
        thread2.start();
    }
}
