package com.chow.arch.concurrent.mid.design016;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by shelvin chow on 2017/5/22.
 */
public class Consumer implements Runnable
{
    private BlockingQueue<Data> blockingQueue;
    private Random random = new Random();

    public Consumer(BlockingQueue<Data> blockingQueue)
    {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run()
    {
        while (true)
        {
            Data data = null;
            try
            {
                data = blockingQueue.take();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            try
            {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("current consumer thread  consumes data success, id is "+data.getId());
        }
    }
}
