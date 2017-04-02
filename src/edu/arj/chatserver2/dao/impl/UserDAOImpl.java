/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arj.chatserver2.dao.impl;

import edu.arj.chatserver2.dao.UserDAO;
import edu.arj.chatserver2.entity.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zeppelin
 */
public class UserDAOImpl implements UserDAO {

    private List<User> userList = new ArrayList<>();

    @Override
    public List<User> getAll() {
        return userList;
    }

    @Override
    public boolean insert(User user) {
        return userList.add(user);
    }

    @Override
    public User getById(int id) {
        for (User u : userList) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    @Override
    public int insertId() {
        int size = userList.size();
        return (size == 0) ? 1 : userList.get(size - 1).getId() + 1;
    }

    @Override
    public boolean isUserNameTaken(String userName) {
        for (User u : userList) {
            if (u.getUserName().equalsIgnoreCase(userName)) {
                return true;
            }
        }
        return false;
    }

}
