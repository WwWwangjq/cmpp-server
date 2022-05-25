package com.tianwen.enums;

import lombok.Getter;

/**
 * wangjq
 * 2022年05月22日  15:33
 */
@Getter
public enum CommandEnum {

    /** 请求连接 */
    CMPP_CONNECT(0x00000001),
    /** 请求连接应答 */
    CMPP_CONNECT_RESP(0x80000001),

    /** 终止连接 */
    CMPP_TERMINATE(0x00000002),
    /** 终止连接应答 */
    CMPP_TERMINATE_RESP(0x80000002),

    /** 提交短信 */
    CMPP_SUBMIT(0x00000004),
    /** 提交短信应答 */
    CMPP_SUBMIT_RESP(0x80000004),

    /** 短信下发 */
    CMPP_DELIVER(0x00000005),
    /** 下发短信应答 */
    CMPP_DELIVER_RESP(0x80000005),

    /** 激活测试 */
    CMPP_ACTIVE_TEST(0x00000008),
    /** 激活测试应答 */
    CMPP_ACTIVE_TEST_RESP(0x80000008),

    ;

    CommandEnum(Integer id) {
        this.id = id;
    }

    private Integer id;

    public static CommandEnum getCommand(Integer id) {
        for (CommandEnum commandEnum : values()) {
            if (commandEnum.getId().equals(id))  {
                return commandEnum;
            }
        }
        throw new IllegalArgumentException();
    }

}
