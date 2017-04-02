/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arj.chatserver2;

import edu.arj.chatserver2.dao.impl.InboxMessageDAOImpl;
import edu.arj.chatserver2.dao.impl.UserDAOImpl;
import edu.arj.chatserver2.util.ClientHandler;
import edu.arj.chatserver2.util.ClientListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Zeppelin
 */
public class Program {
    
    public static void main(String[] args) {
        int port = 9000;
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server running at port " + port);
            ClientHandler handler = new ClientHandler(new UserDAOImpl());
            while (true) {
                Socket socket = server.accept();
                System.out.println(socket.getInetAddress().getHostAddress() + " is now connected.");
                ClientListener listener = new ClientListener(socket, handler);
                listener.start();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
