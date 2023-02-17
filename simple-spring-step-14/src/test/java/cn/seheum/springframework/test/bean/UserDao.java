package cn.seheum.springframework.test.bean;

import cn.seheum.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author seheum 2023/2/17
 */
@Component
public class UserDao {
    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "我爱罗，香港，地头蛇");
        hashMap.put("10002", "旗木卡卡西，广东，茂名");
        hashMap.put("10003", "自来也，广西，桂林");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
