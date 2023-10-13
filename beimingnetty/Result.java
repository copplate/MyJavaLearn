package com.turing.im;

import com.alibaba.fastjson2.JSON;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private String name;
    private String message;

    public static TextWebSocketFrame fail(String message) {
        return new TextWebSocketFrame(JSON.toJSONString(new Result("系统消息", "连接失败" + System.currentTimeMillis() + message)));
    }

    public static TextWebSocketFrame success(String message) {
        return new TextWebSocketFrame(JSON.toJSONString(new Result("系统消息", "连接成功" + System.currentTimeMillis() + message)));
    }

    public static TextWebSocketFrame success(String user,String message) {
        return new TextWebSocketFrame(JSON.toJSONString(new Result(user + "系统消息", "连接成功" + System.currentTimeMillis())));
    }
}
