package com.chow.arch.concurrent.advanced.concurrent018;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by shelvin chow on 2017/5/22.
 */
public class UserThreadPoolExecutor1
{
    public static void main(String[] args)
    {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,
                2,
                60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),new MyRejected());

        MyTask myTask1 = new MyTask(1, "mission 1");
        MyTask myTask2 = new MyTask(2, "mission 2");
        MyTask myTask3 = new MyTask(3, "mission 3");
        MyTask myTask4 = new MyTask(4, "mission 4");
        MyTask myTask5 = new MyTask(5, "mission 5");
        MyTask myTask6 = new MyTask(6, "mission 6");

        threadPoolExecutor.execute(myTask1);
        threadPoolExecutor.execute(myTask2);
        threadPoolExecutor.execute(myTask3);
        threadPoolExecutor.execute(myTask4);
        threadPoolExecutor.execute(myTask5);
        threadPoolExecutor.execute(myTask6);

        threadPoolExecutor.shutdown();
    }
}
