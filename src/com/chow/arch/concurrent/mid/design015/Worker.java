package com.chow.arch.concurrent.mid.design015;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by shelvin chow on 2017/5/21.
 */
public class Worker implements Runnable
{
    private ConcurrentLinkedQueue<Task> workQueue;
    private ConcurrentHashMap<String, Object> resultMap;

    public void setWorkQueue(ConcurrentLinkedQueue<Task> workQueue)
    {
        this.workQueue = workQueue;
    }

    public void setResultMap(ConcurrentHashMap<String, Object> resultMap)
    {
        this.resultMap = resultMap;
    }

    @Override
    public void run()
    {
        while (true)
        {
            Task input = workQueue.poll();
            if (input == null)
            {
                break;
            }
            Object result = this.handler(input);
            resultMap.put(Integer.toString(input.getId()), result);
        }
    }

    private Object handler(Task task)
    {
        try
        {
            Thread.sleep(500);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        return task.getPrice();
    }
}
