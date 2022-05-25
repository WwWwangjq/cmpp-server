package com.tianwen.handler.adapter;

import com.tianwen.model.cmpp.CmppHead;
import com.tianwen.service.AbstractCmppService;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * wangjq
 * 2022年05月23日  22:21
 */
public class CmppDecoder extends ByteToMessageDecoder {

    private List<AbstractCmppService> cmppServiceList;

    public CmppDecoder(List<AbstractCmppService> cmppServiceList) {
        this.cmppServiceList = cmppServiceList;
    }


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Integer totalLength = in.readInt();
        Integer commandId = in.readInt();
        Integer sequenceId = in.readInt();
        for (AbstractCmppService cmppService : cmppServiceList) {
            if (cmppService.support(commandId)) {
                cmppService.execute(totalLength, commandId, sequenceId, in, ctx.channel());
            }
        }
    }
}
