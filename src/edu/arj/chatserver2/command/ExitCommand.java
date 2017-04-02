/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arj.chatserver2.command;

import java.io.IOException;

/**
 *
 * @author Zeppelin
 */
public class ExitCommand extends ChatCommand {

    @Override
    public void execute(String[] tokens) throws IOException {
        client.getSocket().close();
        handler.removeClient(client);
        broadcastMessage(client.getUser().getUserName()+" has logged out.");
    }
    
}
