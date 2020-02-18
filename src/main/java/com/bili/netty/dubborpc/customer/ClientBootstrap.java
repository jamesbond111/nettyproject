package com.bili.netty.dubborpc.customer;

import com.bili.netty.dubborpc.netty.NettyClient;
import com.bili.netty.dubborpc.publicinterface.HelloService;

/**
 * ClientBootstrap 启动NettyClient
 */
public class ClientBootstrap {
    //定义协议头
    public static final String providerName = "HelloService#hello#";
    public static void main(String[] args) throws  Exception{
        //创建一个消费者
        NettyClient customer = new NettyClient();

        //创建代理对象
        HelloService service = (HelloService) customer.getBean(HelloService.class, providerName);

        for (;; ) {
            Thread.sleep(2 * 1000);
            //当调用hello方法时，实际上会被代理到生成的代理对象中，执行指定的操作：
            // 1. 与服务端建立channle连接
            // 2. 设置好协议+参数
            // 3. 发送数据，等待，并获得响应结果
            String res = service.hello("你好 dubbo~");
            System.out.println("调用的结果 res= " + res);
        }
    }
}
