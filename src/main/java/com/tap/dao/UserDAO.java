package com.tap.dao;

import java.util.List;
import com.tap.model.User;

public interface UserDAO {

    boolean addUser(User user);

    User getUserById(int id);

    User getUserByUsername(String username);

    List<User> getAllUsers();

    boolean updateUser(User user);

    boolean deleteUser(int id);
}