package com.bili.netty.simple;

import io.netty.util.NettyRuntime;

/**
 * 获取服务器的核数（4核）
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(NettyRuntime.availableProcessors());
    }
}
