package com.oleman.androidtasks.Firebase;

import java.util.Date;

/**
 * Этот класс макет, или модель сообщения
 */
public class CVData {

    private String name;
    private String email;
    private String message;
    private long timeMessage;

    public CVData(String email, String message, String name) {
        this.name = name;
        this.email = email;
        this.message = message;

        timeMessage = new Date().getTime();

    }

    public CVData() {
    }

    public CVData(String s) {
        this.name = s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeMessage() {
        return timeMessage;
    }

    public void setTimeMessage(long timeMessage) {
        this.timeMessage = timeMessage;
    }

}