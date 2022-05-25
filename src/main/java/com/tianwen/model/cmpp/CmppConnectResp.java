package com.tianwen.model.cmpp;

import com.tianwen.enums.CommandEnum;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * wangjq
 * 2022年05月22日  15:42
 */
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CmppConnectResp extends AbstractCmppHead {
    /**
     * 状态
     * 0：正确
     * 1：消息结构错
     *  2：非法源地址
     *  3：认证错
     *  4：版本太高
     *   5~ ：其他错误
     */
    private byte status;

    /**
     * 16 字节
     * ISMG认证码，用于鉴别ISMG。
     * 其值通过单向MD5 hash计算得出，表示如下：
     * AuthenticatorISMG =MD5（Status+AuthenticatorSource+shared secret），Shared secret 由中国移动与源地址实体事先商定，AuthenticatorSource为源地址实体发送给ISMG的对应消息CMPP_Connect中的值。
     *  认证出错时，此项为空
     */
    private byte[] authenticatorISMG;

    /** 服务器支持的最高版本号 */
    private byte version;

    public CmppConnectResp(boolean result, Integer sequenceId) {
        status = (byte) (result ? 0 : 3);
        super.sequenceId = sequenceId;
    }

    @Override
    public ByteBuf toByteArray() {
        int length = 30;
        ByteBuf byteBuf = Unpooled.buffer(length);
        byteBuf.writeInt(length)
                .writeInt(CommandEnum.CMPP_CONNECT_RESP.getId())
                .writeInt(sequenceId)
                .writeByte(status)
                .writeBytes(new byte[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0})
                .writeByte(version)
                ;
        return byteBuf;
    }
}
