/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arj.chatserver2.util;

import edu.arj.chatserver2.dao.InboxMessageDAO;
import edu.arj.chatserver2.dao.UserDAO;
import edu.arj.chatserver2.entity.User;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zeppelin
 */
public class ClientHandler {

    private List<Client> clients = new ArrayList<>();
    private UserDAO userDAO;
//    private InboxMessageDAO inboxDAO;

    public ClientHandler(UserDAO userDAO) {
        this.userDAO = userDAO;
//        this.inboxDAO = inboxDAO;
    }

    public void addClient(Client c) {
        clients.add(c);
    }

    public void removeClient(Client c) {
        clients.remove(c);
    }

    public List<Client> getClients() {
        return clients;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public Client getByUserName(String userName) {
        for (Client c : clients) {
            if (c.getUser().getUserName().equalsIgnoreCase(userName)) {
                return c;
            }
        }
        return null;
    }

    public Client login(String userName, String password, Socket socket) {
        for (User user : userDAO.getAll()) {
            if (user.getUserName().equalsIgnoreCase(userName)
                    && user.getPassword().equals(password)) {
                Client client = new Client();
                client.setSocket(socket);
                client.setUser(user);
                client.setStatus("ONLINE");
                clients.add(client);
                return client;
            }
        }
        return null;
    }

}
