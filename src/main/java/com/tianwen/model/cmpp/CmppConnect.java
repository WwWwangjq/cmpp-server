package com.tianwen.model.cmpp;

import com.tianwen.enums.AccountEnum;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;

/**
 * wangjq
 * 2022年05月22日  15:41
 */
//@ToString(callSuper = true)
//@EqualsAndHashCode(callSuper = true)
@Getter
public class CmppConnect extends CmppHead {

    /** 6字节 源地址，此处为SP_Id，即SP的企业代码。 */
    private byte[] sourceAddr;
    /** 16字节 用于鉴别源地址。其值通过单向MD5 hash计算得出，
     * 表示如下：AuthenticatorSource = MD5（Source_Addr+9 字节的0 +shared secret+timestamp）
     * Shared secret 由中国移动与源地址实体事先商定，timestamp格式为：MMDDHHMMSS，即月日时分秒，10位。*/
    private byte[] authenticatorSource;
    /** 双方协商的版本号(高位4bit表示主版本号,低位4bit表示次版本号) */
    private byte version;
    /** 时间戳的明文,由客户端产生,格式为MMDDHHMMSS，即月日时分秒，10位数字的整型，右对齐 。 */
    private Integer timestamp;

    public String convertUsername() {
        return new String(sourceAddr);
    }

    public CmppConnect(Integer totalLength,
                       Integer commandId,
                       Integer sequenceId,
                       byte[] sourceAddr,
                       byte[] authenticatorSource,
                       byte version,
                       Integer timestamp) {
        super(totalLength, commandId, sequenceId);
        this.sourceAddr = sourceAddr;
        this.authenticatorSource = authenticatorSource;
        this.version = version;
        this.timestamp = timestamp;
    }

    public boolean validUserSuccess() {
        if (Objects.isNull(sourceAddr) || Objects.isNull(authenticatorSource) || Objects.isNull(timestamp)) {
            return false;
        }
        return Arrays.equals(authenticatorSource, buildAuthenticatorSource());
    }

    private byte[] buildAuthenticatorSource() {
        try {
            ByteBuf byteBuf = Unpooled.buffer(16);
            byteBuf.writeBytes(sourceAddr);
            byteBuf.writeBytes(new byte[] {0, 0, 0, 0, 0, 0});
            byteBuf.writeBytes(AccountEnum.getAccount(convertUsername()).getPassword().getBytes());
            byteBuf.writeInt(timestamp);
            return MessageDigest.getInstance("MD5").digest(byteBuf.array());
        } catch (NoSuchAlgorithmException e) {
            return new byte[0];
        }
    }
}
