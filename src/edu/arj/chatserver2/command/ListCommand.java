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
public class ListCommand extends ChatCommand {

    @Override
    public void execute(String[] tokens) throws IOException {
        StringBuilder content = new StringBuilder();
        selfOut.println("Current Users:");
        selfOut.println("**************");
        for (Client c : handler.getClients()) {
            if (!c.isBlocked(client)) {
                content.append(c.getUser().getUserName()).append(" (").append(c.getStatus()).append(")").append("\r\n");
            }
        }
        selfOut.println(content.toString());
        selfOut.println("**************");
    }

}
