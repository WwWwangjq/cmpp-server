package com.tianwen.service;

import com.tianwen.enums.AccountEnum;
import com.tianwen.model.SmsLink;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * wangjq
 * 2022年05月24日  21:15
 */
@Service
public class InnerLinkService {

    private final Map<String, List<SmsLink>> map = new ConcurrentHashMap<>();

    @PostConstruct
    public void initLink() {
        AccountEnum[] values = AccountEnum.values();
        for (AccountEnum account : values) {
            map.put(account.getUsername(), new ArrayList<>());
        }
    }

    public void saveLink(String username, SmsLink link) {
        List<SmsLink> smsLinks = map.get(username);
        smsLinks.add(link);
    }

}
