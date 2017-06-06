package com.chow.arch.utils;

import org.hyperic.sigar.*;

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

    private static void memory() throws SigarException
    {
        Sigar sigar = new Sigar();
        Mem mem = sigar.getMem();
        System.out.println("内存总量：" + mem.getTotal() / 1024l + "K ava");
        System.out.println("当前内存使用量：" + mem.getUsed() / 2014l + "K used");
        System.out.println("当前内存剩余量：" + mem.getFree() / 2014l + "K free");

        Swap swap = sigar.getSwap();
        System.out.println("交换区总量" + swap.getTotal() / 2014l + "K av");
        System.out.println("当前交换区使用量：" + swap.getUsed() / 2014l + "K used");
        System.out.println("当前交换区剩余量：" + swap.getFree() / 2014l + "K free");
    }

    private static void cpu() throws SigarException
    {
        Sigar sigar = new Sigar();
        CpuInfo[] cpuInfo = sigar.getCpuInfoList();
        CpuPerc cpuList[] = null;

        System.out.println("cpu总量参数情况：" + sigar.getCpu());
        System.out.println("cpu总百分比情况：" + sigar.getCpuPerc());


        cpuList = sigar.getCpuPercList();
        for (int i = 0; i < cpuInfo.length; i++)
        {
            CpuInfo info = cpuInfo[i];
            System.out.println("第" + (i + 1) + "块CPU信息");
            System.out.println("CPU的总量MHZ：" + info.getMhz());
            System.out.println("CPU生产商：" + info.getVendor());
            System.out.println("CPU类别：" + info.getModel());
            System.out.println("CPU缓存数量：" + info.getCacheSize());
            printCpuPerc(cpuList[i]);
        }
    }

    private static void printCpuPerc(CpuPerc cpu)
    {
        System.out.println("CPU用户使用率：" + CpuPerc.format(cpu.getUser()));
        System.out.println("CPU系统使用率：" + CpuPerc.format(cpu.getSys()));
        System.out.println("CPU当前等待率：" + CpuPerc.format(cpu.getWait()));
        System.out.println("CPU当前错误率：" + CpuPerc.format(cpu.getNice()));
        System.out.println("CPU当前空闲率：" + CpuPerc.format(cpu.getIdle()));
        System.out.println("CPU总使用率：" + CpuPerc.format(cpu.getCombined()));
    }

    private static void os()
    {
        OperatingSystem system = OperatingSystem.getInstance();

        System.out.println("操作系统：" + system.getArch());
        System.out.println("操作系统CpuEndian：" + system.getCpuEndian());
        System.out.println("操作系统DataModel：" + system.getDataModel());
        System.out.println("操作系统描述：" + system.getDescription());
        System.out.println("操作系统生产商：" + system.getVendor());
        System.out.println("操作系统生产商CodeName：" + system.getVendorCodeName());
        System.out.println("system vendor name :" + system.getVendorName());
        System.out.println("system vendor version :" + system.getVendorVersion());
        System.out.println("system version : " + system.getVersion());
    }

    private static void who() throws SigarException
    {
        Sigar sigar = new Sigar();
        Who[] whos = sigar.getWhoList();

        if (whos != null && whos.length > 0)
        {
            for (int i = 0; i < whos.length; i++)
            {
                Who who = whos[i];
                System.out.println("用户控制台：" + who.getDevice());
                System.out.println("用户host：" + who.getHost());
                System.out.println("当前系统进程表中的用户名：" + who.getUser());
            }
        }
    }

    private static void file() throws SigarException
    {
        Sigar sigar = new Sigar();
        FileSystem[] fileSystemList = sigar.getFileSystemList();
        for (int i = 0; i < fileSystemList.length; i++)
        {
            System.out.println("分区盘符名称" + i);
            FileSystem fileSystem = fileSystemList[i];
            System.out.println("盘符名称：" + fileSystem.getDevName());
            System.out.println("盘符路径：" + fileSystem.getDirName());
            System.out.println("盘符表示：" + fileSystem.getFlags());
            System.out.println("盘符类型：" + fileSystem.getSysTypeName());
            System.out.println("盘符类型名：" + fileSystem.getTypeName());
            System.out.println("盘符文件系统类型：" + fileSystem.getType());

            FileSystemUsage usag = sigar.getFileSystemUsage(fileSystem.getDirName());

            switch (fileSystem.getType())
            {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    System.out.println(fileSystem.getDevName() + "总大小：" + usag.getTotal() + "Kb");
                    System.out.println(fileSystem.getDevName() + "剩余大小：" + usag.getFree() + "Kb");
                    System.out.println(fileSystem.getDevName() + "可用大小：" + usag.getAvail() + "Kb");
                    System.out.println(fileSystem.getDevName() + "已经使用量：" + usag.getUsed() + "Kb");

                    double usePercent = usag.getUsePercent();
                    System.out.println(fileSystem.getDevName() + "资源利用率：" + usePercent);

                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
            }

            System.out.println(fileSystem.getDevName() + "读出" + usag.getDiskReads());
            System.out.println(fileSystem.getDevName() + "写入" + usag.getDiskWrites());
        }

        return;
    }

    private static void net() throws SigarException
    {
        Sigar sigar = new Sigar();
        String[] ifNames = sigar.getNetInterfaceList();

        for (int i = 0; i < ifNames.length; i++)
        {
            String name = ifNames[i];
            NetInterfaceConfig ifConfig = sigar.getNetInterfaceConfig(name);

            System.out.println("网络设备名：" + name);
            System.out.println("IP地址：" + ifConfig.getAddress());
            System.out.println("子网掩码：" + ifConfig.getNetmask());

            if ((ifConfig.getFlags() & 1L) <= 0l)
            {
                System.out.println("IFF_UP...skipping getNetInterfaceStat");
                continue;
            }

            NetInterfaceStat ifStat = sigar.getNetInterfaceStat(name);
            System.out.println(name + "接收的总包数：" + ifStat.getRxPackets());
            System.out.println(name + "发送的总包数：" + ifStat.getTxPackets());
            System.out.println(name + "接受到的总字节数：" + ifStat.getRxBytes());
            System.out.println(name + "发送的总字节数：" + ifStat.getTxBytes());
            System.out.println(name + "接受到错误数：" + ifStat.getRxErrors());
            System.out.println(name + "接受到错误数：" + ifStat.getRxErrors());
            System.out.println(name + "发送数据的错误数：" + ifStat.getTxErrors());
            System.out.println(name + "接受丢弃的包数：" + ifStat.getRxDropped());
            System.out.println(name + "发送丢弃的包数：" + ifStat.getTxDropped());
        }
    }

    private static void ethernet() throws SigarException
    {
        Sigar sigar = new Sigar();
        String[] ifaces = sigar.getNetInterfaceList();

        for (int i = 0; i < ifaces.length; i++)
        {
            sigar.getNetInterfaceConfig(ifaces[i]);
        }
    }
}



















