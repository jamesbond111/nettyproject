package com.bili.netty.protocoltcp;


/**
 * 自定义协议包，规定了长度，这样服务端就知道每次读取多少字节了
 */
public class MessageProtocol {
    private int len; //关键
    private byte[] content;

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
