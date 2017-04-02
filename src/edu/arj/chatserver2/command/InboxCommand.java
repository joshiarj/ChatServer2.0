/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arj.chatserver2.command;

import edu.arj.chatserver2.entity.InboxMessage;
import edu.arj.chatserver2.util.Client;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Zeppelin
 */
public class InboxCommand extends ChatCommand {

//    private List<String> commands = Arrays.asList("VIEW", "DELETE");
//    private List<String> commands = Arrays.asList("ALL", "ID", "FROM");
    @Override
    public void execute(String[] tokens) throws IOException {
        if (tokens.length > 1) {
            if (tokens[1].equalsIgnoreCase("view")) {
                selfOut.println(client.getUser().getUserName() + "'S INBOX");
                if (tokens[2].equalsIgnoreCase("all")) {
                    viewAll();
                } else if (tokens[2].equalsIgnoreCase("from")) {
                    selfOut.print("Enter sender's name: ");
                    viewMsgsFrom(handler.getByUserName(input.readLine()));
                }
            } else if (tokens[1].equalsIgnoreCase("delete")) {
                if (tokens[2].equalsIgnoreCase("all")) {
                    deleteAllMsgs();
                } else if (tokens[2].equalsIgnoreCase("from")) {
                    selfOut.print("Enter sender's name: ");
                    deleteMsgsFrom(handler.getByUserName(input.readLine()));
                }
            } else {
                selfOut.println("Not a valid command!");
            }
        } else {
            selfOut.println("Not Enough Parameters!");
        }
    }

    private void displayMsgs(InboxMessage im) {
        selfOut.println("*********************");
        selfOut.println("ID: " + im.getId());
        selfOut.println("Date: " + im.getDate());
        selfOut.println("From: " + im.getSender());
        selfOut.println("Message: " + im.getMessage());
        selfOut.println("*********************");
        im.setReadStatus(true);
    }

    private void viewAll() {
        selfOut.println("Showing all messages:");
        if (client.getAllMsgs().size() > 0) {
            for (InboxMessage im : client.getAllMsgs()) {
                displayMsgs(im);
            }
        } else {
            selfOut.println("*********************");
            selfOut.println("No message to show!");
            selfOut.println("*********************");
        }
    }

    private void viewMsgsFrom(Client sender) throws IOException {
        if (sender != null) {
            selfOut.println("Showing all messages from " + sender.getUser().getUserName() + ":");
            for (InboxMessage im : client.getAllMsgs()) {
                if (im.getSender().equals(sender.getUser().getUserName())) {
                    displayMsgs(im);
                } else {
                    selfOut.println("*********************");
                    selfOut.println("No message from " + sender.getUser().getUserName() + ".");
                    selfOut.println("*********************");
                }
            }
        } else {
            selfOut.println("User not found!");
        }
    }

    private void deleteAllMsgs() throws IOException {
        if (client.getAllMsgs().size() > 0) {
            viewAll();
            selfOut.println("Do you really want to empty your inbox? [Y/N]");
            if (input.readLine().equalsIgnoreCase("y")) {
                for (InboxMessage im : client.getAllMsgs()) {
                    client.deleteMsgFromInbox(im);
                }
                selfOut.println("Your inbox is now empty!");
            } else {
                selfOut.println("Nothing deleted!");
            }
        } else {
            selfOut.println("Your inbox is empty. Nothing to delete!");
        }
    }

    private void deleteMsgsFrom(Client sender) throws IOException {
        if (client.getAllMsgs().size() > 0) {
            viewMsgsFrom(sender);
            selfOut.println("Do you really want to delete message(s)? [Y/N]");
            if (input.readLine().equalsIgnoreCase("y")) {
                for (InboxMessage im : client.getAllMsgs()) {
                    if (im.getSender().equals(sender.getUser().getUserName())) {
                        client.deleteMsgFromInbox(im);
                    }
                }
                selfOut.println("All messages from " + sender.getUser().getUserName() + " deleted!");
            } else {
                selfOut.println("Nothing deleted!");
            }
        } else {
            selfOut.println("Your inbox is empty. Nothing to delete!");
        }
    }

}
