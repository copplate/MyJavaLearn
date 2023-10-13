package com.turing.im.handler;

import com.alibaba.fastjson2.JSON;
import com.turing.im.Command;
import com.turing.im.CommandType;
import com.turing.im.Result;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

//泛型<TextWebSocketFrame>，表示是由哪一个对象来承载消息
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame frame) throws Exception {
        //后面的消息读取都在这个channelRead0里

        //System.out.println(textWebSocketFrame.text());
        //要保存映射关系的话，在建立链接时，要把状态信息存起来
        //1、首先要解析frame，我们可以定义一种序列化的格式，比如可以前后端约定好用json的方式来处理消息
        //1、第一步，解析成Command
        try {
            Command command = JSON.parseObject(frame.text(), Command.class);
            switch (CommandType.match(command.getCode())) {
                //case CONNECTION -> ConnectionHandler.execute();//这种写法得14+才支持
                case CONNECTION:
                    ConnectionHandler.execute(ctx,command);
                    break;
                case CHAT:
                    ChatHandler.execute(ctx,frame);
                    break;
                default:
                    //都不支持的话就返回一条错误消息
                    ctx.channel().writeAndFlush(Result.fail("不支持的CODE"));
                    break;
            }
        } catch (Exception e) {
            ctx.channel().writeAndFlush(Result.fail(e.getMessage()));
        }


        //获取到Channel去写入一条消息



    }
}
