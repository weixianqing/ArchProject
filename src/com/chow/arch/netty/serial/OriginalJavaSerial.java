package com.chow.arch.netty.serial;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shelvin on 01/06/2017.
 */
public class OriginalJavaSerial
{

    public static void main(String[] args) throws IOException
    {
        long start = System.currentTimeMillis();
        setSerializableObject();
        System.out.println("java serializable write object time:" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        getSerializableObject();
        System.out.println("java serializable read object time:" + (System.currentTimeMillis() - start));
    }

    public static void setSerializableObject() throws IOException
    {
        FileOutputStream fos = new FileOutputStream("/Users/shelvin/Projects/ArchProject/zhou");
        ObjectOutputStream ops = new ObjectOutputStream(fos);

        for (int i = 0; i < 1000; i++)
        {
            Map<String, Integer> map = new HashMap<String, Integer>(2);
            map.put("zhou0", i);
            map.put("zhou1", i);
            ops.writeObject(new Simple("zhou" + i, i + 1, map));
        }

        ops.flush();
        ops.close();
    }

    public static void getSerializableObject()
    {
        FileInputStream fis;

        try
        {
            fis = new FileInputStream("/Users/shelvin/Projects/ArchProject/zhou");
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (((Simple) ois.readObject()) != null)
            {

            }

            fis.close();
            ois.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
