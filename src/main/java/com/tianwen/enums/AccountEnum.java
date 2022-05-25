package com.tianwen.enums;

import lombok.Getter;

/**
 * wangjq
 * 2022年05月22日  14:57
 */
@Getter
public enum AccountEnum {

    /** 账号1 */
    ACCOUNT1("aaaaal", "j18sh5", 800),
    /** 账号2 */
    ACCOUNT2("aaaaa2", "a81ng7", 1000),
    ;

    AccountEnum(String username, String password, Integer qps) {
        this.username = username;
        this.password = password;
        this.qps = qps;
    }

    private String username;
    private String password;
    /** 总qps */
    private Integer qps;



    public static AccountEnum getAccount(String username) {
        for (AccountEnum accountEnum : values()) {
            if (accountEnum.getUsername().equals(username)) {
                return accountEnum;
            }
        }
        throw new IllegalArgumentException();
    }
}
