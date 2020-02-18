package com.bili.netty.customencoderdecoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    // pipeline中handler的顺序为：【MyLongToByteEncoder（head）-MyByteToLongDecoder-MyClientHandler(tail)】
    // 在客户端的角度而言，入站顺序：head->tail, 出站顺序：tail-> head
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //加入一个出站的handler 对数据进行一个编码
        pipeline.addLast(new MyLongToByteEncoder());
        //这时一个入站的解码器(入站handler )
        pipeline.addLast(new MyByteToLongDecoder());
        //加入一个自定义的handler， 处理业务
        pipeline.addLast(new MyClientHandler());
    }
}
