package com.wlearn.udp.hspnetpractice.tcpfiledownload;


import com.wlearn.udp.hspnetpractice.upload.StreamUtils;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * 2023/05/29
 * 文件下载的客户端
 * */
public class DownLoadClient {
    public static void main(String[] args) throws Exception {
        //1、接收用户输入，指定下载文件名
        Scanner scanner = new Scanner(System.in);//使用标准输入
        System.out.println("请输入下载文件名");
        String downloadFileName = scanner.next();

        //2、客户端连接服务端，准备发送
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);//这样就相当于连接到了
        //3、获取和Socket相关的输出流
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(downloadFileName.getBytes());
        socket.shutdownOutput();//设置写入结束的标志（重要）
        //4、读取服务端返回的文件（其实就是一个字节数组）
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        byte[] bytes = StreamUtils.streamToByteArray(bis);
        //5、得到一个输出流，准备将bytes写入到磁盘文件
        String filePath = "e:\\" + downloadFileName + ".mp4";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        bos.write(bytes);
        //6、关闭资源
        socket.close();
        outputStream.close();
        bis.close();
        bos.close();
        System.out.println("客户端下载完毕，退出");

    }
}
