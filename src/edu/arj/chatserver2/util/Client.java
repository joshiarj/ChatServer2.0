/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arj.chatserver2.util;

import edu.arj.chatserver2.entity.InboxMessage;
import edu.arj.chatserver2.entity.User;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zeppelin
 */
public class Client {

    private User user;
    private Socket socket;
    private List<Client> blockList = new ArrayList<>();
    private List<InboxMessage> inboxMsgs = new ArrayList<>();
    private String status, autoReplyMsg;

    public Client() {
    }

    public Client(User user, Socket socket) {
        this.user = user;
        this.socket = socket;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAutoReplyMsg() {
        return autoReplyMsg;
    }

    public void setAutoReplyMsg(String autoReplyMsg) {
        this.autoReplyMsg = autoReplyMsg;
    }

    public void block(Client client) {
        if (!isBlocked(client)) {
            blockList.add(client);
        }
    }

    public void unblock(Client client) {
        if (isBlocked(client)) {
            blockList.remove(client);
        }
    }

    public boolean isBlocked(Client client) {
        for (Client c : blockList) {
            if (c.equals(client)) {
                return true;
            }
        }
        return false;
    }

    public List<Client> getAllBlocked() {
        return blockList;
    }

    public Client getBlockedByName(String userName) {
        for (Client c : blockList) {
            if (c.getUser().getUserName().equalsIgnoreCase(userName)) {
                return c;
            }
        }
        return null;
    }

    public boolean addMsgToInbox(InboxMessage im) {
        return inboxMsgs.add(im);
    }

    public boolean deleteMsgFromInbox(InboxMessage im) {
        return inboxMsgs.remove(im);
    }

    public List<InboxMessage> getAllMsgs() {
        return inboxMsgs;
    }

    public int insertId() {
        int size = inboxMsgs.size();
        return (size == 0) ? 1 : inboxMsgs.get(size - 1).getId() + 1;
    }

    public InboxMessage getMsgBySender(Client client) {
        for (InboxMessage im : inboxMsgs) {
            if (im.getSender().equalsIgnoreCase(client.getUser().getUserName())) {
                return im;
            }
        }
        return null;
    }

    public InboxMessage getMsgById(int id) {
        for (InboxMessage im : inboxMsgs) {
            if (im.getId() == id) {
                return im;
            }
        }
        return null;
    }

}
