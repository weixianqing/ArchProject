package com.chow.arch.concurrent.mid.design016;

/**
 * Created by shelvin chow on 2017/5/22.
 */
public final class Data
{
    private String id;
    private String name;

    public Data(String id, String name)
    {
        this.id = id;
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "Data{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
