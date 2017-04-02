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
public class UnblockCommand extends ChatCommand {

    @Override
    public void execute(String[] tokens) throws IOException {
        if (tokens.length > 1) {
            Client toUnblock = client.getBlockedByName(tokens[1]);
            if (toUnblock != null) {
                if (!client.getUser().getUserName().equalsIgnoreCase(tokens[1])) {
                    client.unblock(toUnblock);
                    selfOut.println(toUnblock.getUser().getUserName() + " is now unblocked!");
                } else {
                    selfOut.println("ERROR! You cannot unblock yourself!");
                }
            } else {
                selfOut.println(tokens[1] + " is not in your blocked list!");
            }
        } else {
            selfOut.println("Not enough parameters!");
        }
    }

}
