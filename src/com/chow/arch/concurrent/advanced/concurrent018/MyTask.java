package com.chow.arch.concurrent.advanced.concurrent018;

/**
 * Created by shelvin chow on 2017/5/22.
 */
public class MyTask implements Runnable
{
    private int taskId;
    private String taskName;

    public MyTask(int taskId, String taskName)
    {
        this.taskId = taskId;
        this.taskName = taskName;
    }

    public int getTaskId()
    {
        return taskId;
    }

    public void setTaskId(int taskId)
    {
        this.taskId = taskId;
    }

    public String getTaskName()
    {
        return taskName;
    }

    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }

    @Override
    public void run()
    {
        System.out.println("run task, id:" + this.taskId);
        try
        {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public String toString()
    {
        return Integer.toString(this.taskId);
    }
}
