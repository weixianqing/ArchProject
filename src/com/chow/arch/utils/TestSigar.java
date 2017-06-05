package com.chow.arch.utils;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Properties;

/**
 * Created by shelvin chow on 2017/6/5.
 */
public class TestSigar
{
    public static void main(String[] args)
    {

    }

    public static void property() throws UnknownHostException
    {
        Runtime runTime = Runtime.getRuntime();
        Properties properties = System.getProperties();
        InetAddress address = InetAddress.getLocalHost();
        String ip = address.getHostAddress();
        Map<String, String> map = System.getenv();
        String userName = map.get("USERNAME");
        String computerName = map.get("COMPUTERNAME");
        String userDomain = map.get("USERDOMAIN");

        System.out.println("用户名：     " + userName);
        System.out.println("计算机名：   " + computerName);
        System.out.println("计算机域名： " + userDomain);
        System.out.println("本地IP地址： " + ip);
        System.out.println("本地主机名： " + address.getHostName());
        System.out.println("JVM可以使用的总内存：" + runTime.totalMemory());
        System.out.println("JVM可以使用的剩余内存：" + runTime.freeMemory());
        System.out.println("JVM可以使用的处理器个数：" + runTime.availableProcessors());
        System.out.println("Java的运行环境版本：" + properties.getProperty("java.version"));
        System.out.println("Java的运行环境供应商：" + properties.getProperty("java.vendor"));
        System.out.println("Java供应商URL：" + properties.getProperty("java.vendor.url"));
        System.out.println("Java安装路径：" + properties.getProperty("java.home"));
        System.out.println("JVM规范版本：" + properties.getProperty("java.vm.specification.version"));
        System.out.println("JVM规范供应商：" + properties.getProperty("java.vm.specification.vendor"));
        System.out.println("JVM规范名称：" + properties.getProperty("java.vm.specification.name"));
        System.out.println("JVM版本：" + properties.getProperty("java.vm.version"));
        System.out.println("JVM版本供应商" + properties.getProperty("java.vm.vendor"));
        System.out.println("JVM实现名称" + properties.getProperty("java.vm.name"));
        System.out.println("Java运行时环境规范版本" + properties.getProperty("java.specification.version"));
        System.out.println("Java运行时环境规范供应商：" + properties.getProperty("java.specification.vendor"));
        System.out.println("Java运行时环境规范名称：" + properties.getProperty("java.specification.name"));
        System.out.println("Java类格式版本号：" + properties.getProperty("java.class.version"));
        System.out.println("Java的类路径：" + properties.getProperty("java.class.path"));
        System.out.println("加载库时搜索的路径列表：" + properties.getProperty("java.library.path"));
        System.out.println("默认的临时文件路径：" + properties.getProperty("java.io.tmpdir"));
        System.out.println("一个或多个扩展目录的路径：" + properties.getProperty("java.ext.dirs"));
        System.out.println("操作系统的 名称：" + properties.getProperty("os.name"));
        System.out.println("操作系统的架构：" + properties.getProperty("os.arch"));
        System.out.println("操作系统的版本：" + properties.getProperty("os.version"));
        System.out.println("文件分隔符：" + properties.getProperty("file.separator"));
        System.out.println("路径分隔符：" + properties.getProperty("path.separator"));
        System.out.println("行分隔符：" + properties.getProperty("line.separator"));
        System.out.println("用户账户名称：" + properties.getProperty("user.name"));
        System.out.println("用户主目录：" + properties.getProperty("user.home"));
        System.out.println("用户当前工作目录：" + properties.getProperty("user.dir"));
    }

    public static void memory() throws SigarException
    {
        Sigar sigar = new Sigar();
    }
}






















