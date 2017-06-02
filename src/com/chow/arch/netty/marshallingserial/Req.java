package com.chow.arch.netty.marshallingserial;

import java.io.Serializable;

/**
 * Created by shelvin chow on 2017/6/1.
 */
public class Req implements Serializable
{
    private String id;
    private String name;
    private String requestMessage;
    private byte[] attachment;

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

    public String getRequestMessage()
    {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage)
    {
        this.requestMessage = requestMessage;
    }

    public byte[] getAttachment()
    {
        return attachment;
    }

    public void setAttachment(byte[] attachment)
    {
        this.attachment = attachment;
    }
}
