package com.chow.arch.concurrent.base.conn008;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by shelvin chow on 2017/5/9.
 */
public class ListAdd2
{
    private static List list = new ArrayList();

    public void add()
    {
        list.add("chow");
    }

    public int size()
    {
        return list.size();
    }

    public static void main(String[] args)
    {
        final ListAdd2 listAdd2 = new ListAdd2();
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Thread thread1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                for (int i = 0; i < 10; i++)
                {
                    listAdd2.add();
                    System.out.println("current thread " + Thread.currentThread().getName() + " add one element.");
                    try
                    {
                        Thread.sleep(500);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                    if (5 == listAdd2.size())
                    {
                        System.out.println("notification has been send.");
                        countDownLatch.countDown();
                    }
                }
            }
        }, "t1");

        Thread thread2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                if (5 != listAdd2.size())
                {
                    try
                    {
                        countDownLatch.await();
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }

                System.out.println("current thread " + Thread.currentThread().getName() + " has received notification to stop.");
                throw new RuntimeException();
            }
        }, "t2");

        thread1.start();
        thread2.start();
    }
}
