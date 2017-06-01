package com.chow.arch.concurrent.base.conn009;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by shelvin chow on 2017/5/10.
 */
public class MyQueue
{
    private LinkedList linkedList = new LinkedList();
    private AtomicInteger count = new AtomicInteger(0);

    private final int minSize = 0;
    private final int maxSize;

    private final Object lock = new Object();

    public MyQueue(int size)
    {
        maxSize = size;
    }

    public void put(Object object)
    {
        synchronized (lock)
        {
//            if (count.get() < maxSize)
//            {
//                linkedList.add(object);
//                count.incrementAndGet();
//                lock.notify();
//                System.out.println("add one element:" + object);
//            }
//            try
//            {
//                lock.wait();
//            } catch (InterruptedException e)
//            {
//                e.printStackTrace();
//            }


//            if (count.get() >= maxSize)
//            {
//                try
//                {
//                    lock.wait();
//                } catch (InterruptedException e)
//                {
//                    e.printStackTrace();
//                }
//            }
//
//            linkedList.add(object);
//            count.incrementAndGet();
//            lock.notify();
//            System.out.println("add one element:" + object);


            while (count.get() >= maxSize)
            {
                try
                {
                    lock.wait();
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

            linkedList.add(object);
            count.incrementAndGet();
            lock.notify();
            System.out.println("add one element:" + object);
        }
    }

    public Object take()
    {
        Object rem = null;
        synchronized (lock)
        {
            while (this.minSize == count.get())
            {
                try
                {
                    lock.wait();
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            rem = linkedList.removeFirst();
            count.decrementAndGet();
            lock.notify();
        }
        return rem;
    }

    public int getSize()
    {
        return this.count.get();
    }

    public static void main(String[] args)
    {
        final MyQueue queue = new MyQueue(5);
        queue.put("a");
        queue.put("b");
        queue.put("c");
        queue.put("d");
        queue.put("e");

        System.out.println("len of current container:"+ queue.getSize());

        Thread thread1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                queue.put("f");
                queue.put("g");
            }
        }, "t1");

        thread1.start();

        Thread thread2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                Object object1 = queue.take();
                System.out.println("take element:" + object1);
                Object object2 = queue.take();
                System.out.println("take element:" + object2);
            }
        }, "t2");

        try
        {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        thread2.start();
    }
}
