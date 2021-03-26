package com.example.springcachebasic;

import java.util.List;

public interface UserService {

    User findById(Integer userId);

    User save(User user);

    boolean deleteById(Integer userId);

    List<User> findAll();
}
