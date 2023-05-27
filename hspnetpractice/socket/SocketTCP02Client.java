package com.hspnetpractice.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 2022/12/31
 * 客户端，发送“hello，server”给服务端
 * */
public class SocketTCP02Client {
    public static void main(String[] args) throws IOException {
        //思路
        //1、连接服务端：ip、端口
        //解读：连接本机的9999端口，如果连接成功，返回Socket对象（客户端 和 服务端各有一个socket对象）
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端返回了socket");
        //2、连接上后，生成socket，通过socket.getOutputStream()
        //   得到和socket对象关联的输出流对象
        OutputStream outputStream = socket.getOutputStream();
        //3、通过输出流，写入数据到数据通道
        outputStream.write(new byte[]{'h', 'e'});
        //4、设置结束标记
        socket.shutdownOutput();
//        outputStream.write("hello sever".getBytes());
        //5、获取和socket关联的输入流，读取数据（字节），并显示
        InputStream inputStream = socket.getInputStream();
        byte[] buf = new byte[1024];
        int readLen = 0;
        while ((readLen = inputStream.read(buf)) != -1) {
            System.out.println(new String(buf,0,readLen));//根据读取到的实际长度，显示内容
        }

        //6、关闭流对象和socket，必须关闭
        inputStream.close();
        outputStream.close();
        socket.close();



        System.out.println("提示：客户端退出了");


    }
}
