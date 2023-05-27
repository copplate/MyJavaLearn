package com.hspnetpractice.upload;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 2023/01/26
 * 此类用于显示关于流的读写方法
 * */
public class StreamUtils {
    /**
     * 功能：将输入流转换成byte[],即可以把文件读入到字节数组byte[]
     * @param is
     * @return
     * @throws Exception
     * */
    public static byte[] streamToByteArray(InputStream is) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();// 创建输出流对象
        // 字节数组
        byte[] b = new byte[1024];
        int len;
        while ((len = is.read(b)) != -1) {
            // 循环读取
            // 把读取到的数据，写入 bos
            bos.write(b, 0, len);
        }
        byte[] array = bos.toByteArray();
        bos.close();
        return array;
    }

    public static String streamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {//当读取到null时，就表示结束了
            builder.append(line + "\r\n");
        }
        return builder.toString();
    }
}

