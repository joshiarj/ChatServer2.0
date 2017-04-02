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
public class BlockCommand extends ChatCommand {

    @Override
    public void execute(String[] tokens) throws IOException {
        if (tokens.length > 1) {
            Client toBlock = handler.getByUserName(tokens[1]);
            if (toBlock != null) {
                if (!client.getUser().getUserName().equalsIgnoreCase(tokens[1])) {
                    client.block(toBlock);
                    selfOut.println(toBlock.getUser().getUserName() + " is now blocked!");
                } else {
                    selfOut.println("ERROR! You cannot block yourself!");
                }
            } else {
                selfOut.println(tokens[1] + " user not found!");
            }
        } else {
            selfOut.println("Not enough parameters!");
        }
    }

}
