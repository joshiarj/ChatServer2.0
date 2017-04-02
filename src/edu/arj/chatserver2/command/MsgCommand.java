/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arj.chatserver2.command;

import edu.arj.chatserver2.entity.InboxMessage;
import edu.arj.chatserver2.util.Client;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;

public class MsgCommand extends ChatCommand {

    @Override
    public void execute(String[] tokens) throws IOException {
        if (tokens.length > 2) {
            Client receiver = handler.getByUserName(tokens[1]);
            if (receiver != null) {
                InboxMessage im = new InboxMessage();
                im.setId(receiver.insertId());
                im.setSender(client.getUser().getUserName());
                im.setDate(new Date().toString());
                im.setMessage(tokens[2]);
                im.setReadStatus(false);
                receiver.addMsgToInbox(im);
                selfOut.println("Message successfully sent to " + receiver.getUser().getUserName() + "!");
                PrintStream out = new PrintStream(receiver.getSocket().getOutputStream());
                out.println("New message from " + client.getUser().getUserName() + ".");
            } else {
                selfOut.println("User not found!");
            }
        } else {
            selfOut.println("Not enough parameters!");
        }
    }

}
