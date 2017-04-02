/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arj.chatserver2.util;

import edu.arj.chatserver2.command.ChatCommand;
import edu.arj.chatserver2.command.ChatCommandFactory;
import edu.arj.chatserver2.constant.SystemConstant;
import edu.arj.chatserver2.dao.impl.InboxMessageDAOImpl;
import edu.arj.chatserver2.entity.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientListener extends Thread {

    private Socket socket;
    private BufferedReader input;
    private PrintStream out;
    private Client client;
    private ClientHandler handler;

    public ClientListener(Socket socket, ClientHandler handler) throws IOException {
        this.socket = socket;
        this.handler = handler;
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            out.println("WELCOME TO CHAT SERVER");
            out.println("**********************");
//            out.println("Enter your choice:");
//            String choice = input.readLine();
//            if (choice.equals("1")) {
            register();
//            } else if (choice.equals("2")) {
            login();
//            } else {
//                out.println("Invalid choice.");
//            }
            broadcastMessage(client.getUser().getUserName() + " has logged in.");
            while (!isInterrupted()) {
                out.println("(Me) >");
                String line = input.readLine();
                String[] tokens = line.split(SystemConstant.CHAT_SEPERATOR);
                ChatCommand cmd = ChatCommandFactory.get(tokens[0].toUpperCase());
                if (cmd != null) {
                    cmd.setClient(client);
                    cmd.setHandler(handler);
                    cmd.setSelfOut(out);
                    cmd.setInput(input);
                    cmd.setInboxDAO(new InboxMessageDAOImpl());
                    cmd.execute(tokens);
                } else {
                    broadcastMessage(client.getUser().getUserName() + " says > " + line);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void broadcastMessage(String msg) throws IOException {
        for (Client c : handler.getClients()) {
            if (!c.equals(client) && !c.isBlocked(client) && !client.isBlocked(c)) {
                PrintStream ps = new PrintStream(c.getSocket().getOutputStream());
                ps.println(msg);
            }
        }
    }

    private void register() throws IOException {
        client = new Client();
        User user = new User();
        out.println("REGISTRATION");
        while (true) {
            out.println("Enter Username:");
            String userName = input.readLine();
            if (!handler.getUserDAO().isUserNameTaken(userName)) {
                user.setId(handler.getUserDAO().insertId());
                user.setUserName(userName);
                break;
            } else {
                out.println("Username is already taken! Please try another.");
            }
        }
        out.println("Enter Password:");
        String password = input.readLine();
        user.setPassword(password);
        user.setStatus(true);
        handler.getUserDAO().insert(user);
//        client.setUser(user);
//        client.setSocket(socket);
//        client.setStatus("Status not set");
//        handler.addClient(client);
        out.println("User \'" + user.getUserName() + "\' successfully registered!");
        out.println("**********************");
        File file = new File("users/" + user.getUserName());
        if (!file.isDirectory()) {
            file.mkdir();
        }
    }

    public void login() throws IOException {
        out.println("LOG IN");
        while (true) {
            out.println("Enter username:");
            String userName = input.readLine();
            out.println("Enter password:");
            String password = input.readLine();
            client = handler.login(userName, password, socket);
            if (client != null) {
                break;
            } else {
                out.println("Invalid username/password!");
            }
        }
    }

}
