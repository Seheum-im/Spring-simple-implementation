package cn.seheum.springframework.test.bean;

/**
 * @author seheum 2023/2/17
 */
public interface IUserService {
    String queryUserInfo();

    String register(String userName);
}
