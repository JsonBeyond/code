package com.alex.springboot.flywaydb.user;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author Alex
 * @CreateDate 2019/9/6 16:06
 * @Version 1.0
 */
public interface UserService {

    void create(String name, Integer age);

    void deleteByName(String name);

    Integer getAllUsers() throws Exception;

    void deleteAllUsers();

}
