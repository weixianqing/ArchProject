package com.chow.arch.concurrent.advanced.concurrent018;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by shelvin chow on 2017/5/22.
 */
public class MyRejected implements RejectedExecutionHandler
{
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor)
    {
        System.out.println("my rejected policy.");
        System.out.println("current rejected task is " + r.toString());
    }
}
