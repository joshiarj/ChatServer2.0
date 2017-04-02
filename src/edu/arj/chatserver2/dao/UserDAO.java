/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arj.chatserver2.dao;

import edu.arj.chatserver2.entity.User;
import java.util.List;

/**
 *
 * @author Zeppelin
 */
public interface UserDAO {

    List<User> getAll();

    boolean insert(User user);

    User getById(int id);

    int insertId();

    boolean isUserNameTaken(String userName);
}
