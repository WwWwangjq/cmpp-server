package com.tianwen.model;


import com.tianwen.enums.AccountEnum;
import io.netty.channel.Channel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * wangjq
 * 2022年05月22日  14:49
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmsLink {

    /** netty channel */
    private Channel channel;
    private AccountEnum account;

    public void initChannel(Channel channel) {
        this.channel = channel;
    }

    public boolean login(String username, Integer password) {
        return true;
    }
}
