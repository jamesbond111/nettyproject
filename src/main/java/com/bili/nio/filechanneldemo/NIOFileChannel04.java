package com.bili.nio.filechanneldemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * 利用FileChannel的transferFrom方法完成文件的拷贝
 * 相比与第三个例子，简单了很多，但实际上是将底层操作进行了包装
 */
public class NIOFileChannel04 {
    public static void main(String[] args)  throws Exception {
        //创建相关流
        FileInputStream fileInputStream = new FileInputStream("/Users/libi/Desktop/peiqifamily.jpeg");
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/libi/Desktop/peiqifamily111.jpeg");

        //获取各个流对应的filechannel
        FileChannel srcCh = fileInputStream.getChannel();
        FileChannel dstCh = fileOutputStream.getChannel();

        //使用transferForm完成拷贝
        dstCh.transferFrom(srcCh,0,srcCh.size());
        //关闭相关通道和流
        srcCh.close();
        dstCh.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
