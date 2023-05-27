package com.hspnetpractice.udp;

import java.io.IOException;
import java.net.*;

/**
 * 2023/05/26
 * UDP发送端（注：发送端也可以当接收端，只不过这里为了区分就给了不同的名字）
 * */
public class UDPSenderB {
    public static void main(String[] args) throws IOException {
        //创建DatagramSocket对象，准备在9998接口接收数据
        //9998的含义是如果将来有人给你发送数据，你准备在哪个端口接收数据
        DatagramSocket datagramSocket = new DatagramSocket(9998);

        //2、将需要发送的数据，封装到一个DatagramPacket对象中
        byte[] data = "hello,明天吃火锅~".getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("192.168.123.12"), 9999);

        datagramSocket.send(packet);



        byte[] buf = new byte[1024];
        DatagramPacket packet2 = new DatagramPacket(buf, buf.length);
        //3、调用接收方法
        //如果真的有数据向9999端口发送,就会把通过网络传过来的DatagramPacket对象，填充到receive方法这个空的packet里
        //老师提示：当有数据发送到本机的9999端口，就会继续往下执行解析数据
        //当没有数发送到本机的9999端口，就会在这里阻塞等待
        System.out.println("接收端B 等待接受数据..");
        datagramSocket.receive(packet);

        //4、把packet进行拆包，取出数据并显示
        int length = packet.getLength();//实际接收到的数据字节长度
        byte[] data2 = packet.getData();//接收到的数据
        String s = new String(data, 0, length);
        System.out.println(s);

        //关闭资源
        datagramSocket.close();
        System.out.println("B端退出");

    }
}
