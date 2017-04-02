/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arj.chatserver2.command;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Zeppelin
 */
public class StatusCommand extends ChatCommand {

    private List<String> statusMsg = Arrays.asList("ONLINE", "AWAY", "BUSY", "OFFLINE");
    private String autoReplyMsg = "";

    @Override
    public void execute(String[] tokens) throws IOException {
        if (tokens.length > 1) {
            if (statusMsg.contains(tokens[1].toUpperCase())) {
                client.setStatus(tokens[1].toUpperCase());
                selfOut.println("Status successfully changed to " + client.getStatus());
                if(client.getStatus().equalsIgnoreCase("away")){
                    selfOut.println("Enter autoreply message:");
                    autoReplyMsg = input.readLine();
                    client.setAutoReplyMsg(autoReplyMsg);
                }
            } else {
                selfOut.println(tokens[1] + " is not a valid status! Please try again.");
            }
        } else {
            selfOut.println("Not enough parameters!");
        }
    }

}
