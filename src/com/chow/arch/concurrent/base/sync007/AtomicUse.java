package com.chow.arch.concurrent.base.sync007;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by shelvin chow on 2017/5/7.
 */
public class AtomicUse
{
    private static AtomicInteger count = new AtomicInteger(0);

    public int multiAdd()
    {
        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        count.addAndGet(1);
        count.addAndGet(2);
        count.addAndGet(3);
        count.addAndGet(4);
        return count.get();
    }

    public static void main(String[] args)
    {
        final AtomicUse atomicUse = new AtomicUse();
        ArrayList<Thread> ts = new ArrayList<Thread>();
        for (int i = 0; i < 100; i++)
        {
            ts.add(new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    System.out.println(atomicUse.multiAdd());
                }
            }));
        }

        for (Thread thread : ts)
        {
            thread.start();
        }
    }
}
