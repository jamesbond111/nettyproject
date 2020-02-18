package com.bili.nio.socketchanneldemo;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO服务端
 * 通过这个例子，可以了解到selector是如何使用的
 * <p>
 * 1.服务端创建一个serverSocketchannel,并注册到selector中（注明感兴趣的事件为Accept）,这样就可以接受客户端的连接
 * 2.调用select函数，如果发现有感兴趣的事件就处理，否则通过while循环继续调用select
 * 3.当有客户端连接请求到来时，就创建一个SocketChannel，并注册到selector中（注明感兴趣的事件为Read）
 * 4.继续select轮询：当再次有客户端读事件产生时，则能够获取SelectionKey,通过该key能反推出具体发生事件的channel，以及相关联的Buffer
 */
public class NIOServer {
    public static void main(String[] args) throws Exception {
        //创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        serverSocketChannel.configureBlocking(false);//设置为非阻塞

        //得到一个Selecor对象
        Selector selector = Selector.open();
        //把serverSocketChannel注册到selector,关心的事件为OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("注册后的key数量=" + selector.keys().size()); //
        //循环等待客户端连接
        while (true) {
            //这里我们等待1秒，如果没有事件发生,则继续通过select轮询
            if (selector.select(1000) == 0) { //没有事件发生
                System.out.println("服务器等待了1秒，无连接");
                continue;
            }

            //如果返回的>0, 就获取到相关的selectionKey集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();//返回关注事件的集合
            System.out.println("selectionKeys 数量 = " + selectionKeys.size());

            //遍历 Set<SelectionKey>, 使用迭代器遍历
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                //获取到SelectionKey
                SelectionKey key = keyIterator.next();
                //根据key 对应的通道发生的事件做相应处理
                if (key.isAcceptable()) { //如果是 OP_ACCEPT, 有新的客户端连接
                    //为该客户端生成一个SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端连接成功 生成了一个 socketChannel " + socketChannel.hashCode());
                    //需要将SocketChannel设置为非阻塞
                    socketChannel.configureBlocking(false);
                    //将socketChannel注册到selector,关注事件为 OP_READ，同时给socketChannel关联一个Buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    System.out.println("客户端连接后 ，注册的selectionkey 数量=" + selector.keys().size()); //2,3,4..
                }

                if (key.isReadable()) {  //发生 OP_READ
                    //通过key反向获取到对应channel
                    SocketChannel channel = (SocketChannel) key.channel();
                    //获取到该channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    channel.read(buffer);
                    System.out.println("from 客户端 " + new String(buffer.array()));
                }

                //手动从集合中移动当前的selectionKey, 防止重复操作
                keyIterator.remove();
            }
        }
    }
}
