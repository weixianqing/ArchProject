package com.chow.arch.concurrent.mid.design016;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by shelvin chow on 2017/5/22.
 */
public class Main
{
    public static void main(String[] args)
    {
        BlockingQueue<Data> queue = new LinkedBlockingDeque<Data>();

        Provider provider1 = new Provider(queue);
        Provider provider2 = new Provider(queue);
        Provider provider3 = new Provider(queue);

        Consumer consumer1 = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);
        Consumer consumer3 = new Consumer(queue);

        ExecutorService threadPool = Executors.newCachedThreadPool();
        threadPool.execute(provider1);
        threadPool.execute(provider2);
        threadPool.execute(provider3);
        threadPool.execute(consumer1);
        threadPool.execute(consumer2);
        threadPool.execute(consumer3);

        try
        {
            Thread.sleep(3000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        provider1.stop();
        provider2.stop();
        provider3.stop();

        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
