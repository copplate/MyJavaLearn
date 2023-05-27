package com.hspnetpractice.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 2022/12/31
 * */
public class SocketTCP02Server {
    public static void main(String[] args) throws IOException {
        //思路
        //1、在本机的9999端口监听，等待连接
        //细节：要求本机没有其他服务在监听9999
        ServerSocket serverSocket = new ServerSocket(9999);
        /**
         * ServerSocket和Socket的关系： ServerSocket可以创建很多Socket，有一次accept()就返回一个Socket（多个客户端连接服务器的并发）
         * */
        //2、当没有客户端连接9999端口时，程序会阻塞，等待连接
        //   如果有客户端连接，则会返回Socket对象，程序继续（客户端 和 服务端各有一个socket对象）
        System.out.println("服务端，在9999端口监听，等待连接");
        Socket socket = serverSocket.accept();
        System.out.println("监听到socket，连接完成");
        //3、通过socket.getInputStream（）读取客户端写入到数据通道的数据，并显示
        InputStream inputStream = socket.getInputStream();
        //4、IO读取
        byte[] buf = new byte[1024];
        int readLen = 0;
        while ((readLen = inputStream.read(buf)) != -1) {
            System.out.println(new String(buf,0,readLen));//根据读取到的实际长度，显示内容
        }
        //5、获取socket相关联的输出流
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello client".getBytes());
        //6、设置结束标记
        socket.shutdownOutput();

        //7、关闭流和socket
        outputStream.close();
        inputStream.close();
        socket.close();
        serverSocket.close();


    }
}
