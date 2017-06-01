package com.chow.arch.communication.fakeaio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by shelvin chow on 2017/5/23.
 */
public class HandlerExecutorPool
{
    private ExecutorService executor = null;

    public HandlerExecutorPool(int maxPoolSize,int queueSize)
    {
        this.executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize,
                120L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize));
    }

    public void execute(Runnable task)
    {
        this.executor.execute(task);
    }
}
