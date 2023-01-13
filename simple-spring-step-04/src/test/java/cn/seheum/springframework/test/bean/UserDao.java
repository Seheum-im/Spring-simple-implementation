package cn.seheum.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {

    private static Map<String,String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001","菲奥娜");
        hashMap.put("10002","艾瑞莉娅");
        hashMap.put("10003","锐雯");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
