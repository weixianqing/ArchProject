package com.chow.arch.concurrent.base.conn011;

/**
 * Created by shelvin chow on 2017/5/11.
 */
public class DubbleSingleton
{
    private static DubbleSingleton dubbleSingleton;

    public static DubbleSingleton getDubbleSingleton()
    {
        if (dubbleSingleton == null)
        {
            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            synchronized (DubbleSingleton.class)
            {
                if (dubbleSingleton == null)
                {
                    dubbleSingleton = new DubbleSingleton();
                }
            }
        }

        return dubbleSingleton;
    }

    public static void main(String[] args)
    {
        Thread thread1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println(DubbleSingleton.getDubbleSingleton().hashCode());
            }
        }, "t1");
        Thread thread2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println(DubbleSingleton.getDubbleSingleton().hashCode());
            }
        }, "t2");
        Thread thread3 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println(DubbleSingleton.getDubbleSingleton().hashCode());
            }
        }, "t3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
