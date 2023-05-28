package com.hspnetpractice.tcpfiledownload;


import com.hspnetpractice.upload.StreamUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 2023/05/28
 * */
public class DownLoadServer {
    public static void main(String[] args) throws Exception {
        //1、监听9999端口，等待客户端连接并读取客户端发送的下载文件的名字
        ServerSocket serverSocket = new ServerSocket(9999);
        //2、等待客户端连接，如果没有客户端连接，就阻塞在这里。
        Socket socket = serverSocket.accept();
        //3、读取客户端发送的要下载的文件名
        //这里使用while循环读取文件名，是考虑到文件名较大的情况
        InputStream inputStream = socket.getInputStream();
        byte[] b = new byte[1024];
        int len = 0;
        String downloadFileName = "";
        while ((len = inputStream.read(b))!= -1) {
            downloadFileName += new String(b, 0, len);
        }
        System.out.println("客户端希望下载的文件名是" + downloadFileName);

        //如果找不到客户要下载的文件，一律返回beauty5.mp4
        String resFileName = "";
        if ("beauty4.mp4".equals(downloadFileName)) {
            resFileName = "src\\beauty4.mp4";
        } else {
            resFileName = "src\\beauty5.mp4";
        }

        //4、创建一个输入流，读取文件
        BufferedInputStream bis =
                new BufferedInputStream(new FileInputStream(resFileName));
        //5、使用工具类StreamUtils，读取文件到一个字节数组
        byte[] bytes = StreamUtils.streamToByteArray(bis);
        //6、得到Socket关联的输出流
        BufferedOutputStream bos =
                new BufferedOutputStream(socket.getOutputStream());
        //7、写入到数据通道，返回给客户端
        bos.write(bytes);
        socket.shutdownOutput();//设置结束标记(很关键)

        //8、关闭相关的资源
        serverSocket.close();
        socket.close();
        inputStream.close();
        bis.close();
        bos.close();
        System.out.println("服务端退出");
    }
}
