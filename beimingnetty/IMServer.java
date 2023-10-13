package com.turing.im;

import com.turing.im.handler.WebSocketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IMServer {
    //把映射关系通过一个CoCurrentHashmap保存下来
    public static final Map<String, Channel> USERS = new ConcurrentHashMap<>(1024);//KEY是昵称，value是channel

    public static void start() {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        //通过ServerBootstrap来监听一个端口
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                //初始化Handler
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //ChannelPipeline是一种责任链的方式
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        //添加http编解码器
                        pipeline.addLast(new HttpServerCodec())
                                //添加对大数据量的支持
                                .addLast(new ChunkedWriteHandler())
                                //对http消息做聚合操作,会生成HttpRequest和HttpResponse，其实就是做了一个封装
                                .addLast(new HttpObjectAggregator(1024 * 64))
                                //其实websocket还有一个握手的过程，就要采用netty自带的一个协议的handler
                                .addLast(new WebSocketServerProtocolHandler("/"))
                                //自定义一个消息处理
                                .addLast(new WebSocketHandler());

                    }
                });
        //绑定端口
        ChannelFuture future = bootstrap.bind(8080);
    }
}
