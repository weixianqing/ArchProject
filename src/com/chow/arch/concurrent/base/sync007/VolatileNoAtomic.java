package com.chow.arch.concurrent.base.sync007;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by shelvin chow on 2017/5/7.
 */
public class VolatileNoAtomic extends Thread
{
//    public static volatile int count = 0;
    public static AtomicInteger count = new AtomicInteger();

    public static void addCount()
    {
        for (int i = 0; i < 1000; i++)
        {
            count.incrementAndGet();
//            count++;
        }
        System.out.println(count);
    }

    @Override
    public void run()
    {
        addCount();
    }

    public static void main(String[] args)
    {
        VolatileNoAtomic[] arr = new VolatileNoAtomic[10];
        for (int i = 0; i < 10; i++)
        {
            arr[i] = new VolatileNoAtomic();
        }
        for (int i = 0; i < 10; i++)
        {
            arr[i].start();
        }
    }
}
















