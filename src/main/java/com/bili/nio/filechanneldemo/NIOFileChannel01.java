package com.bili.nio.filechanneldemo;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * FileChannel：往文件中写入数据
 */
public class NIOFileChannel01 {
    public static void main(String[] args) throws Exception {
        String str = "hello,libi";
        //创建一个输出流
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/libi/aa.txt");
        //通过fileOutputStream获取对应的FileChannel
        FileChannel fileChannel = fileOutputStream.getChannel();

        //创建一个缓冲区ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //将str放入byteBuffer
        byteBuffer.put(str.getBytes());

        //对byteBuffer进行flip
        byteBuffer.flip();
        //将byteBuffer数据写入到fileChannel
        fileChannel.write(byteBuffer);

        fileOutputStream.close();
    }
}
