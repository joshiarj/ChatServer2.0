/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arj.chatserver2.command;

import edu.arj.chatserver2.dao.InboxMessageDAO;
import edu.arj.chatserver2.dao.impl.InboxMessageDAOImpl;
import edu.arj.chatserver2.entity.InboxMessage;
import edu.arj.chatserver2.util.Client;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;

/**
 *
 * @author Zeppelin
 */
public class PMCommand extends ChatCommand {
    
    @Override
    public void execute(String[] tokens) throws IOException {
        if (tokens.length > 2) {
            Client pmReceiver = handler.getByUserName(tokens[1]);
            if (pmReceiver != null) {
                if (!pmReceiver.isBlocked(client) && !pmReceiver.equals(client)) {
                    if (!pmReceiver.getStatus().equalsIgnoreCase("away")) {
                        PrintStream out = new PrintStream(pmReceiver.getSocket().getOutputStream());
                        out.println("PM from " + client.getUser().getUserName() + " > " + tokens[2]);
                    } else {
                        selfOut.println(pmReceiver.getUser().getUserName() + " says> " + pmReceiver.getAutoReplyMsg());
                        InboxMessage im = new InboxMessage();
                        im.setId(pmReceiver.insertId());
                        im.setSender(client.getUser().getUserName());
                        im.setDate(new Date().toString());
                        im.setMessage("(PM) " + tokens[2]);
                        im.setReadStatus(false);
                        pmReceiver.addMsgToInbox(im);
                    }
                } else {
                    selfOut.println("Error sending PM!");
                }
            } else {
                selfOut.println("User not found!");
            }
        } else {
            selfOut.println("Not enough parameters!");
        }
    }
    
}
