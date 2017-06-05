package com.chow.arch.utils;


import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by Shelvin on 02/06/2017.
 */
public class GzipUtils
{
    public static byte[] gzip(byte[] data) throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        GZIPOutputStream gos = new GZIPOutputStream(bos);
        gos.write(data);
        gos.finish();
        gos.close();
        byte[] res = bos.toByteArray();
        bos.close();
        return res;
    }

    public static byte[] ungizp(byte[] data) throws IOException
    {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        GZIPInputStream gis = new GZIPInputStream(bis);

        byte[] buf = new byte[1024];
        int count = -1;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while (((count = gis.read(buf, 0, buf.length)) != -1))
        {
            bos.write(buf, 0, count);
        }

        gis.close();
        byte[] res = bos.toByteArray();
        bos.flush();
        bos.close();
        return res;
    }

    public static void main(String[] args) throws IOException
    {
        String readPath = System.getProperty("user.dir") + File.separatorChar + "sources" + File.separatorChar + "006.jpg";
        System.out.println("reading path:" + readPath);
        FileInputStream fis = new FileInputStream(readPath);
        byte[] data = new byte[fis.available()];
        fis.read(data);
        fis.close();

        System.out.println("Original file size:" + data.length);

        byte[] ret1 = GzipUtils.gzip(data);
        System.out.println("compressed size:" + ret1.length);

        byte[] ret2 = GzipUtils.ungizp(ret1);
        System.out.println("decompressed size:" + ret2.length);

        String writePath = System.getProperty("user.dir") + File.pathSeparatorChar + "receive" + File.separatorChar + "006.jpg";
        FileOutputStream fos = new FileOutputStream(writePath);
        fos.write(ret2);
        fos.close();
    }
}
