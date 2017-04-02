package edu.arj.chatserver2.command;

import edu.arj.chatserver2.dao.InboxMessageDAO;
import edu.arj.chatserver2.util.Client;
import edu.arj.chatserver2.util.ClientHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public abstract class ChatCommand {
    
    protected Client client;
    protected PrintStream selfOut;
    protected ClientHandler handler;
    protected BufferedReader input;
    protected InboxMessageDAO inboxDAO;
    
    public void setClient(Client client) {
        this.client = client;
    }
    
    public void setSelfOut(PrintStream selfOut) throws IOException {
        this.selfOut = selfOut;
    }
    
    public void setHandler(ClientHandler handler) {
        this.handler = handler;
    }

    public void setInput(BufferedReader input) {
        this.input = input;
    }

    public void setInboxDAO(InboxMessageDAO inboxDAO) {
        this.inboxDAO = inboxDAO;
    }
    
    public abstract void execute(String[] tokens) throws IOException;
    
    protected void broadcastMessage(String msg) throws IOException {
        for (Client c : handler.getClients()) {
            if (!c.equals(client) && !c.isBlocked(client) && !client.isBlocked(c)) {
                PrintStream ps = new PrintStream(c.getSocket().getOutputStream());
                ps.println(msg);
            }
        }
    }
    
}
