package com.bili.netty.dubborpc.provider;

import com.bili.netty.dubborpc.netty.NettyServer;

/**
 * ServerBootstrap 启动NettyServer
 */
public class ServerBootstrap {
    public static void main(String[] args) {
        NettyServer.startServer("127.0.0.1", 7000);
    }
}
