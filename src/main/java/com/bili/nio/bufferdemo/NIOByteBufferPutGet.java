package com.bili.nio.bufferdemo;

import java.nio.ByteBuffer;

/**
 * Buffer支持类型化的get和put操作
 * get和put操作的数据类型要一致，否则会抛出BufferUnderflowException
 */
public class NIOByteBufferPutGet {
    public static void main(String[] args) {
        //创建一个Buffer
        ByteBuffer buffer = ByteBuffer.allocate(64);

        //类型化方式放入数据
        buffer.putInt(100);
        buffer.putLong(9);
        buffer.putChar('a');
        buffer.putShort((short) 4);

        //取出
        buffer.flip();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());






    }
}
