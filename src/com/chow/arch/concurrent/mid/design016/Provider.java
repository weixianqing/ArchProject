package com.chow.arch.concurrent.mid.design016;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by shelvin chow on 2017/5/22.
 */
public class Provider implements Runnable
{
    private BlockingQueue<Data> blockingQueue;
    private AtomicInteger count = new AtomicInteger();
    private volatile boolean isRunning = true;
    Random random = new Random();

    public Provider(BlockingQueue<Data> blockingQueue)
    {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run()
    {
        while (isRunning)
        {
            try
            {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            int id = count.getAndIncrement();
            Data data = new Data(Integer.toString(id), "Data " + id);
            System.out.println("current thread " + Thread.currentThread().getName() + " get data, id is " + id + ", will be loaded into cache.");
            try
            {
                if (!blockingQueue.offer(data, 2, TimeUnit.SECONDS))
                {
                    System.out.println("load failed.");
                }
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void stop()
    {
        this.isRunning = false;
    }
}
