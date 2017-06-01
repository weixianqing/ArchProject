package com.chow.arch.communication.Buffer;

import java.nio.IntBuffer;

/**
 * Created by shelvin chow on 2017/5/23.
 */
public class BufferDemo
{
    public static void main(String[] args)
    {
        IntBuffer intBuffer = IntBuffer.allocate(10);
        intBuffer.put(13);
        intBuffer.put(21);
        intBuffer.put(35);

//        flip方法是把position置位，上述intBuffer不调用flip是HeapIntBuffer[pos=3 lim=10 cap=10]
//        调用flip方法是HeapIntBuffer[pos=0 lim=3 cap=10]
//        intBuffer.flip();

        System.out.println("IntBuffer:" + intBuffer);
        System.out.println("capacity is " + intBuffer.capacity());
        System.out.println("limit  is " + intBuffer.limit());

        System.out.println("获取下标为1的元素" + intBuffer.get(1));
        System.out.println("get(index)方法，position位置不改变：" + intBuffer);

        intBuffer.put(1, 4);
        System.out.println("put(index,change)方法，position位置不变：" + intBuffer);

        for (int i = 0; i < intBuffer.limit(); i++)
        {
//          get方法分有参数和无参数的，有参数的参数就是buffer的索引，无参数的并不是buffer全部即无参数的get方法并不是
//          从索引0开始取，而是从缓冲区位置（position）向后递增一位开始。
//          如果 不调用flip方法，position是2，所以，get()方法实际上从3开始，做一个向后加一的操作，顺带把值给取出来
//          所以，如果上边没有执行flip方法，该for循环 会报错。
            System.out.println(intBuffer.get() + "\t");
        }
        System.out.println("intBuffer对象遍历之后为：" + intBuffer);


//        wrap方法
        int[] arr = new int[]{1, 2, 5};
        IntBuffer intBuffer1 = IntBuffer.wrap(arr);
        System.out.println(intBuffer1);

        IntBuffer intBuffer2 = IntBuffer.wrap(arr, 0, 2);
        System.out.println(intBuffer2);


//        其他方法
        IntBuffer intBuffer3 = IntBuffer.allocate(10);
        int[] arr1 = new int[]{5, 6, 7};
        intBuffer3.put(arr1);
        System.out.println(intBuffer3);

        IntBuffer intBuffer4 = intBuffer3.duplicate();
        System.out.println(intBuffer4);


        intBuffer3.flip();
        System.out.println(intBuffer3);

        System.out.println("可读数据为："+intBuffer3.remaining());

        int[] arr2 = new int[intBuffer3.remaining()];
//        将缓冲区数据放入arr2数组中去
        intBuffer3.get(arr2);
        for (int i : arr2)
        {
            System.out.println(Integer.toString(i)+",");
        }
    }
}
