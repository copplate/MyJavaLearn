package com.hspnetpractice.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 2022/12/31
 * 客户端，发送“hello，server”给服务端
 * */
public class SocketTCP03Client {
    public static void main(String[] args) throws IOException {
        //思路
        //1、连接服务端：ip、端口
        //解读：连接本机的9999端口，如果连接成功，返回Socket对象（客户端 和 服务端各有一个socket对象）
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端返回了socket");
        //2、连接上后，生成socket，通过socket.getOutputStream()
        //   得到和socket对象关联的输出流对象
        OutputStream outputStream = socket.getOutputStream();
        //3、通过输出流，写入数据到数据通道，使用字符流
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("hello server 字符流");
        bufferedWriter.newLine();//插入一个换行符，表示写入的内容结束（结束标记），此时要求对方使用readLine()方式来读，不然读不到结束符
        bufferedWriter.flush();//如果使用的是字符流，需要手动刷新，否则数据不会写入服务器和客户端之间的数据通道

//        outputStream.write("hello sever".getBytes());
        //5、获取和socket关联的输入流，读取数据（字符），并显示
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println(s);

        //6、关闭流对象和socket，必须关闭
        bufferedReader.close();//关闭外层流
        bufferedWriter.close();
        socket.close();
        System.out.println("提示：客户端退出了");


    }
}
