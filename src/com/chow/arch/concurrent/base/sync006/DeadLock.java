package com.chow.arch.concurrent.base.sync006;

/**
 * Created by shelvin chow on 2017/5/11.
 */
public class DeadLock implements Runnable
{
    private String tag;
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public void setTag(String tag)
    {
        this.tag = tag;
    }

    @Override
    public void run()
    {
        if ("a".equals(tag))
        {
            synchronized (lock1)
            {
                System.out.println("current thread "+Thread.currentThread().getName()+" enters lock1.");
                try
                {
                    Thread.sleep(2000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                synchronized (lock2)
                {
                    System.out.println("current thread "+Thread.currentThread().getName()+" enters lock2.");
                }
            }
        }

        if ("b".equals(tag))
        {
            synchronized (lock2)
            {
                System.out.println("current thread "+Thread.currentThread().getName()+" enters lock2.");
                try
                {
                    Thread.sleep(2000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                synchronized (lock1)
                {
                    System.out.println("current thread "+Thread.currentThread().getName()+" enters lock1.");
                }
            }
        }
    }

    public static void main(String[] args)
    {
        DeadLock deadLock1 = new DeadLock();
        deadLock1.setTag("a");

        DeadLock deadLock2 = new DeadLock();
        deadLock2.setTag("b");

        Thread thread1 = new Thread(deadLock1, "t1");
        Thread thread2 = new Thread(deadLock2, "t2");

        thread1.start();
        try
        {
            Thread.sleep(500);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        thread2.start();
    }
}
