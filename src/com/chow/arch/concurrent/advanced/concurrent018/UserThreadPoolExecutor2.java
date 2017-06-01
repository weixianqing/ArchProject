package com.chow.arch.concurrent.advanced.concurrent018;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by shelvin chow on 2017/5/22.
 */
public class UserThreadPoolExecutor2 implements Runnable
{
    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run()
    {
        int temp = count.incrementAndGet();
        System.out.println("task " + temp);
        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        BlockingQueue<Runnable> queue = new LinkedBlockingDeque<Runnable>();
        ExecutorService executorService = new ThreadPoolExecutor(5, 10, 120L, TimeUnit.SECONDS, queue);

        for (int i = 0; i < 20; i++)
        {
            executorService.execute(new UserThreadPoolExecutor2());
        }

        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("queue size is "+queue.size());
        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

