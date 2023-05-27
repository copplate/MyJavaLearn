package com.hspnetpractice.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * 2023/05/26
 * UDP接收端（注：接收端也可以当发送端，只不过这里为了区分就给了不同的名字）
 * */
public class UDPReceiverA {
    public static void main(String[] args) throws IOException {
        //1、创建一个DatagramSocket对象，准备在9999端口接收数据
        DatagramSocket socket = new DatagramSocket(9999);
        //2、构建一个DatagramPacket对象，准备接收数据
        //前面讲UDP协议时说过，一个数据包最大64K
        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        //3、调用接收方法
        //如果真的有数据向9999端口发送,就会把通过网络传过来的DatagramPacket对象，填充到receive方法这个空的packet里
        //老师提示：当有数据发送到本机的9999端口，就会继续往下执行解析数据
        //当没有数发送到本机的9999端口，就会在这里阻塞等待
        System.out.println("接收端A 等待接受数据..");
        socket.receive(packet);

        //4、把packet进行拆包，取出数据并显示
        int length = packet.getLength();//实际接收到的数据字节长度
        byte[] data = packet.getData();//接收到的数据
        String s = new String(data, 0, length);
        System.out.println(s);

        //5、关闭资源
//        socket.close();
//        System.out.println("A端退出");

        //2、将需要发送的数据，封装到一个DatagramPacket对象中
        byte[] data2 = "好的，明天见".getBytes();
        DatagramPacket packet2 = new DatagramPacket(data2, data2.length, InetAddress.getByName("192.168.123.12"), 9998);

        socket.send(packet2);

        //关闭资源
        socket.close();

    }
}
