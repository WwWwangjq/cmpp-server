package com.tianwen.handler.adapter;

import com.tianwen.model.cmpp.AbstractCmppHead;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * wangjq
 * 2022年05月23日  22:20
 */
public class CmppEncoder extends MessageToByteEncoder<AbstractCmppHead> {

    @Override
    protected void encode(ChannelHandlerContext ctx, AbstractCmppHead msg, ByteBuf out) throws Exception {
        ctx.writeAndFlush(msg.toByteArray());
    }
}
