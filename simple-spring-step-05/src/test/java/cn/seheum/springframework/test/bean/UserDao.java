package cn.seheum.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    public static Map<String,String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "张学友");
        hashMap.put("10002", "许嵩");
        hashMap.put("10003", "郑智化");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
