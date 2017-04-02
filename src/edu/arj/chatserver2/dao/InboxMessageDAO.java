/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arj.chatserver2.dao;

import edu.arj.chatserver2.entity.InboxMessage;
import java.util.List;

/**
 *
 * @author Zeppelin
 */
public interface InboxMessageDAO {
    List<InboxMessage> getAllMsgs();
    String showAllMessages();
    InboxMessage getById(int id);
    boolean insertMsg(InboxMessage msg);
    boolean deleteMsg(InboxMessage msg);
    int insertId();
}
