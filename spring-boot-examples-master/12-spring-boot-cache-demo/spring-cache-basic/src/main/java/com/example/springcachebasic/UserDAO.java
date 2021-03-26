package com.example.springcachebasic;

import java.util.List;

public interface UserDAO {

    boolean save(User user);

    User findById(Integer id);

    boolean deleteById(Integer id);

    List<User> findAll();
}
