package com.chow.arch.concurrent.base.conn008;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shelvin chow on 2017/5/9.
 */
public class ListAdd1
{
    private volatile static List list = new ArrayList();

    public void add()
    {
        list.add("shelvin");
    }

    public int size()
    {
        return list.size();
    }

    public static void main(String[] args)
    {
        final ListAdd1 listAdd1 = new ListAdd1();

        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                for (int i = 0; i < 10; i++)
                {
                    listAdd1.add();
                    System.out.println("current thread:" + Thread.currentThread().getName() + " add one element");
                    try
                    {
                        Thread.sleep(500);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1");

        Thread thread1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (true)
                {
                    if (listAdd1.size() == 5)
                    {
                        System.out.println("current thread receive notification:" + Thread.currentThread().getName()
                                + " list size = 5, thread stopped.");
                        throw new RuntimeException();
                    }
                }
            }
        }, "t2");


        thread.start();
        thread1.start();
    }
}
