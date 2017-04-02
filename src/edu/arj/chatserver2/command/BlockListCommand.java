/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arj.chatserver2.command;

import edu.arj.chatserver2.util.Client;
import java.io.IOException;

/**
 *
 * @author Zeppelin
 */
public class BlockListCommand extends ChatCommand {

    @Override
    public void execute(String[] tokens) throws IOException {
        StringBuilder content = new StringBuilder();
        selfOut.println("Your Blocked List:");
        selfOut.println("******************");
        for (Client c : client.getAllBlocked()) {
            content.append(c.getUser().getUserName()).append(" (").append(c.getStatus()).append(")").append("\r\n");
        }
        if (!content.toString().equalsIgnoreCase("")) {
            selfOut.println(content.toString());
        } else {
            selfOut.println("Your blocked list is empty!");
        }
        selfOut.println("******************");
    }

}
