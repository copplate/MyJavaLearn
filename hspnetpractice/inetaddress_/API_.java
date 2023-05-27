package com.hspnetpractice.inetaddress_;

import java.net.InetAddress;
import java.net.UnknownHostException;
/**
 * 2022/12/29
 * */
public class API_ {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress name = InetAddress.getByName("www.baidu.com");
        System.out.println(name);
    }
}
