/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arj.chatserver2.entity;

public class InboxMessage {
    private int id;
    private String sender;
    private String date;
    private String message;
    private boolean readStatus;

    public InboxMessage() {
    }

    public InboxMessage(int id, String sender, String message) {
        this.id = id;
        this.sender = sender;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isReadStatus() {
        return readStatus;
    }

    public void setReadStatus(boolean readStatus) {
        this.readStatus = readStatus;
    }

    @Override
    public String toString() {
//        StringBuilder content = new StringBuilder();
        return "InboxMessage{" + "\nid=" + id + ", \nsender=" + sender + ", \ndate=" + date + ", \nmessage=" + message + ", \nreadStatus=" + readStatus + "\n}";
    }
    
}
