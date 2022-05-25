package com.tianwen.model.cmpp;

import lombok.Getter;

/**
 * wangjq
 * 2022年05月22日  15:28
 */
@Getter
public class CmppHead {

    /** 消息总长度(含消息头及消息体) */
    protected Integer totalLength;
    /** 命令或响应类型 */
    protected Integer commandId;
    /** 消息流水号,顺序累加,步长为1,循环使用（一对请求和应答消息的流水号必须相同） */
    protected Integer sequenceId;
    protected CmppHead(){}
    protected CmppHead(Integer totalLength, Integer commandId, Integer sequenceId) {
        this.totalLength = totalLength;
        this.commandId = commandId;
        this.sequenceId = sequenceId;
    }


}
