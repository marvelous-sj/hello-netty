package xyz.marsj.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Description: WebSocketInitializer
 * @Author: Marvelous_SJ
 * @Create: 2019-09-01 09:54
 **/
public class WebSocketInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //webSocket 基于http协议，添加http编解码器
        pipeline.addLast(new HttpServerCodec());
        //support for writing a large data stream
        pipeline.addLast(new ChunkedWriteHandler());
        //对httpMessage消息进行聚合，聚合成FullHttpRequest或FullHttpResponse
        //在netty编程中几乎都会使用到此handler
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));
        //webSocket访问协议,客户端路由路径/marsj,一定要加上/
        //webSocket中都是以frames进行传输的，不同的数据对应的frames也不同
        pipeline.addLast(new WebSocketServerProtocolHandler("/marsj"));
        //自定义handler
        pipeline.addLast(new CustomHandler());
    }
}
