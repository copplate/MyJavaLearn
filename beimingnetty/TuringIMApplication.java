package com.turing.im;

import com.alibaba.fastjson2.JSON;
import org.example.Main;
import org.junit.Test;

public class TuringIMApplication {
    public static void main(String[] args) {
        IMServer.start();
    }

    @Test
    public void getString() {
        String s = JSON.toJSONString(new Command(10001, "接天莲叶无穷碧"));
        System.out.println(s);

    }
}
