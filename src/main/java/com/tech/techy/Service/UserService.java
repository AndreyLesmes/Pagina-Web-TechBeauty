package com.tech.techy.Service;

import java.util.List;

import com.tech.techy.Entity.User;

public interface UserService {
    List<User> findAll();

    User findById(int pkId);

    void create(User user);

    void update(User user);

    void delete(User user);
}
