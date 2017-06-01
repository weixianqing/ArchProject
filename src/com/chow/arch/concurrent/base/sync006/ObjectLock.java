package com.chow.arch.concurrent.base.sync006;

/**
 * Created by shelvin chow on 2017/5/11.
 */
public class ObjectLock
{
    public void method1()
    {
        synchronized (this)
        {
            System.out.println("do method 1.");
            try
            {
                Thread.sleep(2000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void method2()
    {
        synchronized (ObjectLock.class)
        {
            System.out.println("do method 2.");
            try
            {
                Thread.sleep(2000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    private Object object = new Object();
    public void method3()
    {
        synchronized (object)
        {
            System.out.println("do method 3.");
            try
            {
                Thread.sleep(2000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
    {
        final ObjectLock objectLock = new ObjectLock();

        Thread thread1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                objectLock.method1();
            }
        });

        Thread thread2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                objectLock.method2();
            }
        });

        Thread thread3 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                objectLock.method3();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
