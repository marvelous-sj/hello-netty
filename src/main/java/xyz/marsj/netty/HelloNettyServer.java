package xyz.marsj.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Description:
 * @Author: sj
 * @Create: 2018-11-12 21:39
 **/
public class HelloNettyServer {
    public static void main(String[] args)  {
        //接受客户端连接，不怎么做事
        NioEventLoopGroup parentGroup =new NioEventLoopGroup();
        //真正处理任务的线程组
        NioEventLoopGroup childGroup =new NioEventLoopGroup();
        try {
        //netty 服务器的创建
        //启动类
        ServerBootstrap serverBootstrap=new ServerBootstrap();
        serverBootstrap.group(parentGroup,childGroup).channel(NioServerSocketChannel.class)
                .childHandler(new HelloNettyServerInit());//子处理器，处理childGroup
        //同步方式启动
        ChannelFuture channelFuture = serverBootstrap.bind(8787).sync();
        //关闭监听的channel
        channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }
}
