package xyz.marsj.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * @Description: CustomHandler
 * @Author: Marvelous_SJ
 * @Create: 2019-09-01 10:14
 **/
public class CustomHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //客户端发送的消息
        String content = msg.text();
        System.out.println("消息为:"+content);

        clients.writeAndFlush(new TextWebSocketFrame(LocalDateTime.now() + "服务端接受到消息,消息为:" + content));
        //两种操作效果一致
//        for (Channel channel : clients) {
//            channel.writeAndFlush(LocalDateTime.now() + "服务端接受到消息,消息为:" + content);
//        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
        System.out.println("客户端连接，ip为:"+ctx.channel().remoteAddress());
        System.out.println("客户端连接，长id为:"+ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //ChannelGroup会自动移除客户端的channel
        //clients.remove(ctx.channel());
        System.out.println("客户端断开，ip为:"+ctx.channel().remoteAddress());
        System.out.println("客户端断开，长id为:"+ctx.channel().id().asLongText());
    }
}
