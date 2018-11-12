package xyz.marsj;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpObject;

/**
 * @Description: 自定义助手类,用于返回hello netty
 * @Author: sj
 * @Create: 2018-11-12 21:47
 **/
//请求入站
public class CustomHander extends SimpleChannelInboundHandler<HttpObject> {
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

    }
}
