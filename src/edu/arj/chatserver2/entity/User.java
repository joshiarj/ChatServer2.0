/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arj.chatserver2.entity;

import edu.arj.chatserver2.dao.InboxMessageDAO;
import edu.arj.chatserver2.dao.impl.InboxMessageDAOImpl;

/**
 *
 * @author Zeppelin
 */
public class User {
    private int id;
    private String userName, password;
    private boolean status;
    private InboxMessage myInbox;
    private InboxMessageDAO inboxDAO;

    public User() {
    }

    public User(int id, String userName, String password, boolean status) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.status = status;
//        inboxDAO = new InboxMessageDAOImpl();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public InboxMessage getMyInbox() {
        return myInbox;
    }

    public void setMyInbox(InboxMessage myInbox) {
        this.myInbox = myInbox;
    }

    public InboxMessageDAO getInboxDAO() {
        return inboxDAO;
    }

    public void setInboxDAO(InboxMessageDAO inboxDAO) {
        this.inboxDAO = inboxDAO;
    }
    
}
