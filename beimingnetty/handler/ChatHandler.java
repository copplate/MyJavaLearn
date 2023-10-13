package com.turing.im.handler;

import com.alibaba.fastjson2.JSON;
import com.turing.im.Command;
import com.turing.im.Result;
import com.turing.im.command.ChatMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class ChatHandler {

    public static void execute(ChannelHandlerContext ctx, TextWebSocketFrame frame) {
        try {
            //1、第一步，把消息的格式转成ChatMessage
            ChatMessage chat = JSON.parseObject(frame.text(), ChatMessage.class);
            //接收到消息，就拿到了目标对象，然后再判断是私聊消息还是群聊消息
        } catch (Exception e) {
            ctx.channel().writeAndFlush(Result.fail("发送消息格式错误，请确认后再试"));
        }


    }
}
