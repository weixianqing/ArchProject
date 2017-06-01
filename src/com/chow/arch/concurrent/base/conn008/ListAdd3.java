package com.chow.arch.concurrent.base.conn008;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shelvin chow on 2017/5/9.
 */
public class ListAdd3
{
    private final static List list = new ArrayList();

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
        final ListAdd3 listAdd3 = new ListAdd3();

        final Object lock = new Object();

        Thread thread1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                synchronized (lock)
                {
                    for (int i = 0; i < 10; i++)
                    {
                        listAdd3.add();
                        System.out.println("current thread " + Thread.currentThread().getName() + " add one element.");
                        try
                        {
                            Thread.sleep(500);
                        } catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }

                        if (5 == listAdd3.size())
                        {
                            System.out.println("notification has been send.");
                            lock.notify();
                        }
                    }
                }
            }
        }, "t1");

        Thread thread2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                synchronized (lock)
                {
                    if (5 != listAdd3.size())
                    {
                        try
                        {
                            System.out.println("enter thread t2");
                            lock.wait();
                        } catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }

                    System.out.println("current thread " + Thread.currentThread().getName() + " received notification to stop.");
                    throw new RuntimeException();
                }
            }
        }, "t2");

        thread2.start();
        try
        {
            Thread.sleep(5);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        thread1.start();
    }
}
