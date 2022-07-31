package com.ioco.iocoassessmentbackend.util;

public class Message {
    private String message;

    public static Message create(String message) {
        return new Message(message);
    }

    private Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

