package cn.seheum.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "阿甘左");
        hashMap.put("10002", "西岚");
        hashMap.put("10003", "GSD");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
