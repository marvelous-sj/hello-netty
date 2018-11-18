package xyz.marsj;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @Description: 自定义助手类,用于返回hello netty
 * @Author: sj
 * @Create: 2018-11-12 21:47
 **/
//请求入站
public class CustomHander extends SimpleChannelInboundHandler<HttpObject> {
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        Channel channel = ctx.channel();
        if(msg instanceof HttpRequest)
        {
            //显示客户端ip地址
            System.out.println(channel.remoteAddress());
            //发送的消息
            ByteBuf content = Unpooled.copiedBuffer("hello netty~~", CharsetUtil.UTF_8);
            //添加响应
            FullHttpResponse response=new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,content);
            //响应头和长度的添加
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
            //把响应刷新到客户端
            ctx.writeAndFlush(response);
        }

    }
}
