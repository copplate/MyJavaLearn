package com.hspnetpractice.upload;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 2023/01/26
 * 文件上传的服务端
 * */
public class TCPFileUploadServer {
    public static void main(String[] args) throws Exception {
        //首先把服务端的监听做起来
        //1.服务端在本机监听8888端口
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务端在8888端口监听");
        //2.等待连接（如果有客户端来连接成功，我们会得到一个socket）
        Socket socket = serverSocket.accept();

        //3.读取客户端发送的数据
        //通过socket得到输入流
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        byte[] bytes = StreamUtils.streamToByteArray(bis);
        //4.将得到的bytes数组，写入到指定的路径，就得到一个文件了
        String destFilePath = "src\\qie2.jpg";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFilePath));
        bos.write(bytes);
        bos.close();
        //向客户端回复收到图片
        //通过socket获取到输出流（字符）
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write("收到图片");
        writer.flush();//把内容刷新到数据通道
        socket.shutdownOutput();//设置结束标记


        //关闭其他资源
        bis.close();
        socket.close();
        serverSocket.close();
        writer.close();

    }
}
