package com.chow.arch.concurrent.base.sync006;

/**
 * Created by shelvin chow on 2017/5/10.
 */
public class ChangeLock
{
    private String lock = "lock";

    public void method()
    {
        synchronized (lock)
        {
            System.out.println("current thread " + Thread.currentThread().getName() + " begin.");
            lock = "change lock";
            try
            {
                Thread.sleep(2000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("current thread " + Thread.currentThread().getName() + " stop.");
        }
    }

    public static void main(String[] args)
    {
        final ChangeLock changeLock = new ChangeLock();

        Thread thread1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                changeLock.method();
            }
        }, "t1");

        Thread thread2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                changeLock.method();
            }
        }, "t2");

        thread1.start();

        try
        {
            Thread.sleep(100);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        thread2.start();
    }
}
