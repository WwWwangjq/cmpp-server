package com.tianwen.bootstrap;

import com.tianwen.handler.adapter.CmppDecoder;
import com.tianwen.handler.adapter.CmppEncoder;
import com.tianwen.service.AbstractCmppService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * wangjq
 * 2022年05月23日  22:10
 */
@Component
public class CmppBootstrap {

    private static final int PORT = 7890;
    @Autowired
    private List<AbstractCmppService> cmppServices;

    @PostConstruct
    public void start() {
        new Thread(() -> {
            try {
                run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class) // (3)
                    .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast(new DiscardServerHandler());
                            ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, -4, 0, true));
                            ch.pipeline().addLast(new CmppDecoder(cmppServices));
                            ch.pipeline().addLast(new CmppEncoder());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(PORT).sync(); // (7)
//            b.bind(PORT); // (7)
            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(0x00000001 == 1);
    }
}
