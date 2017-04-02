/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arj.chatserver2.dao.impl;

import edu.arj.chatserver2.dao.InboxMessageDAO;
import edu.arj.chatserver2.entity.InboxMessage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zeppelin
 */
public class InboxMessageDAOImpl implements InboxMessageDAO {

    private List<InboxMessage> inbox = new ArrayList<>();

    @Override
    public List<InboxMessage> getAllMsgs() {
        return inbox;
    }

    @Override
    public InboxMessage getById(int id) {
        for (InboxMessage i : inbox) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    @Override
    public boolean insertMsg(InboxMessage msg) {
        return inbox.add(msg);
    }

    @Override
    public boolean deleteMsg(InboxMessage msg) {
        return inbox.remove(msg);
    }

    @Override
    public int insertId() {
        int size = inbox.size();
        return (size == 0) ? 1 : inbox.get(size - 1).getId() + 1;
    }

    @Override
    public String showAllMessages() {
        String content = "";
        if (inbox.size() != 0) {
            for (InboxMessage i : inbox) {
                content = "ID: " + i.getId() + "\nDate: " + i.getDate()
                        + "\nFrom: " + i.getSender() + "\nMessage: " + i.getMessage()
                        + "\nStatus:" + i.isReadStatus();
            }
        } else {
            content = "No new messages in your inbox!";
        }
        return content.replaceAll("\\R\\s+", "\\R");
    }

}
