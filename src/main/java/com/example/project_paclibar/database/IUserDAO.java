package com.example.project_paclibar.database;

import com.example.project_paclibar.model.User;

public interface IUserDAO {
    User login(String username, String password);
    boolean register(User user);
}