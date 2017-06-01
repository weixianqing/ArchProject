package com.chow.arch.concurrent.mid.design015;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by shelvin chow on 2017/5/19.
 */
public class Master
{
    private ConcurrentLinkedQueue<Task> workQueue = new ConcurrentLinkedQueue<Task>();
    private HashMap<String, Thread> workers = new HashMap<String, Thread>();
    private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();

    public Master(Worker worker, int workerCount)
    {
        worker.setWorkQueue(this.workQueue);
        worker.setResultMap(this.resultMap);

        for (int i = 0; i < workerCount; i++)
        {
            workers.put(Integer.toString(i), new Thread(worker));
        }
    }

    public void submit(Task task)
    {
        this.workQueue.add(task);
    }

    public void execute()
    {
        for (Map.Entry<String, Thread> me :
                workers.entrySet())
        {
            me.getValue().start();
        }
    }

    public boolean isComplete()
    {
        for (Map.Entry<String, Thread> me : workers.entrySet())
        {
            if (me.getValue().getState() != Thread.State.TERMINATED)
            {
                return false;
            }
        }

        return true;
    }

    public int getReault()
    {
        int priceResult = 0;
        for (Map.Entry<String, Object> me : resultMap.entrySet())
        {
            priceResult += (Integer) me.getValue();
        }

        return priceResult;
    }
}




















