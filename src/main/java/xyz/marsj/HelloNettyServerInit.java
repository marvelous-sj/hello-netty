package xyz.marsj;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Description:
 * @Author: sj
 * @Create: 2018-11-12 21:39O
 **/
public class HelloNettyServerInit extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel channel) throws Exception {
        //获得相应管道
        ChannelPipeline pipeline = channel.pipeline();
        //http编解码
        pipeline.addLast("HttpServerCodec",new HttpServerCodec());
        //自定义助手类,此处用于返回hello netty
        pipeline.addLast("CustomHander",new CustomHander());
    }
}
