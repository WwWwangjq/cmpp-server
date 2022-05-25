package com.tianwen.service;

import com.tianwen.model.cmpp.CmppHead;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.springframework.stereotype.Service;

/**
 * wangjq
 * 2022年05月23日  22:25
 */
@Service
public class CmppSubmitService extends AbstractCmppService {

    @Override
    public boolean support(Integer commandId) {
        return false;
    }

    @Override
    public void execute(Integer totalLength, Integer commandId, Integer sequenceId, ByteBuf in, Channel channel) {

    }
}
