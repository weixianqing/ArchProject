package com.chow.arch.concurrent.base.coll013;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by shelvin chow on 2017/5/18.
 */
public class PriorityBlockingQueueDemo
{
    public static void main(String[] args) throws InterruptedException
    {
        final PriorityBlockingQueue<Task> priorityBlockingQueue = new PriorityBlockingQueue<Task>();

        Task task1 = new Task();
        task1.setId(3);
        task1.setName("id is 3");

        Task task2 = new Task();
        task2.setId(4);
        task2.setName("id is 4");

        Task task3 = new Task();
        task3.setId(1);
        task3.setName("id is 1");

        Task task4 = new Task();
        task4.setId(9);
        task4.setName("id is 9");

        Task task5 = new Task();
        task5.setId(6);
        task5.setName("id is 6");

        priorityBlockingQueue.add(task1);
        priorityBlockingQueue.add(task2);
        priorityBlockingQueue.add(task3);
        priorityBlockingQueue.add(task4);
        priorityBlockingQueue.add(task5);

        System.out.println("container:" + priorityBlockingQueue);

        System.out.println(priorityBlockingQueue.take().getId());
        System.out.println("container:" + priorityBlockingQueue);

        System.out.println(priorityBlockingQueue.take().getId());
        System.out.println("container:" + priorityBlockingQueue);

        System.out.println(priorityBlockingQueue.take().getId());
        System.out.println("container:" + priorityBlockingQueue);

        System.out.println(priorityBlockingQueue.take().getId());
        System.out.println("container:" + priorityBlockingQueue);

        System.out.println(priorityBlockingQueue.take().getId());
        System.out.println("container:" + priorityBlockingQueue);
    }
}
