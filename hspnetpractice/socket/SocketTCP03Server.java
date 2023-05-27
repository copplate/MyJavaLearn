package com.hspnetpractice.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 2022/12/31
 * */
public class SocketTCP03Server {
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
        //4、IO读取，使用字符流(使用转换流InputStreamReader将inputStream转成字符流)
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println(s);//输出
        //5、获取socket相关联的输出流
        OutputStream outputStream = socket.getOutputStream();
        //6、使用字符输出流的方式回复信息
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("hello client 字符流");
        bufferedWriter.newLine();
        bufferedWriter.flush();//手动刷新一下，否则数据不会被写入数据通道


        //7、关闭流和socket
        bufferedWriter.close();//一般关流是后打开的先关闭
        bufferedReader.close();
        socket.close();
        serverSocket.close();


    }
}
