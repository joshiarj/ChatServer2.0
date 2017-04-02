/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arj.chatserver2.entity;

/**
 *
 * @author Zeppelin
 */
public class Email {
    private int id;
    private String date, sender, subject, message;
    private boolean readStatus;

    public Email() {
    }

    public Email(int id, String date, String sender, String subject, String message, boolean readStatus) {
        this.id = id;
        this.date = date;
        this.sender = sender;
        this.subject = subject;
        this.message = message;
        this.readStatus = readStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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
    
}
