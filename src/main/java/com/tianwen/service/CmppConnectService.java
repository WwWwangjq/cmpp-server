package com.tianwen.service;

import com.tianwen.enums.AccountEnum;
import com.tianwen.enums.CommandEnum;
import com.tianwen.model.SmsLink;
import com.tianwen.model.cmpp.CmppConnect;
import com.tianwen.model.cmpp.CmppConnectResp;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * wangjq
 * 2022年05月23日  22:25
 */
@Service
public class CmppConnectService extends AbstractCmppService {

    @Autowired
    private InnerLinkService innerLinkService;

    @Override
    public boolean support(Integer commandId) {
        return CommandEnum.CMPP_CONNECT.getId().equals(commandId);
    }

    @Override
    public void execute(Integer totalLength, Integer commandId, Integer sequenceId, ByteBuf in, Channel channel) {
        CmppConnect connect = convert(totalLength, commandId, sequenceId, in);
        boolean result = connect.validUserSuccess();
        if (result) {
            String username = connect.convertUsername();
            SmsLink smsLink = SmsLink.builder()
                    .account(AccountEnum.getAccount(username))
                    .channel(channel)
                    .build();
            innerLinkService.saveLink(username, smsLink);
        }
        channel.writeAndFlush(new CmppConnectResp(result, sequenceId));
    }

    private CmppConnect convert(Integer totalLength, Integer commandId, Integer sequenceId, ByteBuf in) {
        byte[] sourceAddr = new byte[6];
        byte[] authenticatorSource = new byte[16];
        in.readBytes(sourceAddr);
        in.readBytes(authenticatorSource);
        return new CmppConnect(totalLength, commandId, sequenceId,
                sourceAddr,
                authenticatorSource,
                in.readByte(),
                in.readInt()
        );
    }

    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeBytes(new byte[]{(byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7});
    }
}
