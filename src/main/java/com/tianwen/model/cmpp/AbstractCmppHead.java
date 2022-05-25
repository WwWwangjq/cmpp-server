package com.tianwen.model.cmpp;

import io.netty.buffer.ByteBuf;
import lombok.Getter;

/**
 * wangjq
 * 2022年05月22日  15:28
 */
@Getter
public abstract class AbstractCmppHead extends CmppHead {

    public abstract ByteBuf toByteArray();
}
