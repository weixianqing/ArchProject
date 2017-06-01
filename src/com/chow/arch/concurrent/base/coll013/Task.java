package com.chow.arch.concurrent.base.coll013;

/**
 * Created by shelvin chow on 2017/5/18.
 */
public class Task implements Comparable<Task>
{
    private int id;
    private String name;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public int compareTo(Task o)
    {
        return this.id > o.id ? 1 : (this.id < o.id ? -1 : 0);
    }

    @Override
    public String toString()
    {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
