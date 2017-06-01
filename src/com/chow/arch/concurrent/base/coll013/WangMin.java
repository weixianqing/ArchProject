package com.chow.arch.concurrent.base.coll013;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by shelvin chow on 2017/5/18.
 */
public class WangMin implements Delayed
{
    private String name;
    private String id;
    private long endTime;

    private TimeUnit timeUnit = TimeUnit.SECONDS;

    public WangMin(String name, String id, long endTime)
    {
        this.name = name;
        this.id = id;
        this.endTime = endTime;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Override
    public long getDelay(TimeUnit unit)
    {
        return endTime - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o)
    {
        WangMin wangMin = (WangMin) o;
        return this.getDelay(timeUnit) > wangMin.getDelay(timeUnit) ? 1 : 0;
    }
}
