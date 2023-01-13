package cn.seheum.springframework.test.bean;

public class UserService {

    private String uId;

    private UserDao userDao;

    public void queryInfo() {
        System.out.println("查询用户信息：" + userDao.queryUserName(uId));
    }
}
