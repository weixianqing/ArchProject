package com.chow.arch.netty.marshallingserial;

import java.io.Serializable;

/**
 * Created by shelvin chow on 2017/6/1.
 */
public class Resp implements Serializable
{
    private String id;
    private String name;
    private String responseMessage;

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

    public String getResponseMessage()
    {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage)
    {
        this.responseMessage = responseMessage;
    }
}
