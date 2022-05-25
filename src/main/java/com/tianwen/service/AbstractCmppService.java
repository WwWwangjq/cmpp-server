package com.tianwen.service;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

/**
 * wangjq
 * 2022年05月23日  22:24
 */
public abstract class AbstractCmppService {

    public abstract boolean support(Integer commandId);

    public abstract void execute(Integer totalLength, Integer commandId, Integer sequenceId, ByteBuf in, Channel channel);
}
