package com.example.dating.Chat;

public class ChatObject {
    private String messages;
    private Boolean currentUser;
    private Boolean isSeen;
    private String imageURL;

    public ChatObject(String messages, Boolean currentUser, Boolean isSeen) {
        this.messages = messages;
        this.currentUser = currentUser;
        this.isSeen = isSeen;
    }

    public ChatObject(String messages, Boolean currentUser, Boolean isSeen, String imageURL) {
        this.messages = messages;
        this.currentUser = currentUser;
        this.isSeen = isSeen;
        this.imageURL = imageURL;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public Boolean getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Boolean currentUser) {
        this.currentUser = currentUser;
    }

    public Boolean getSeen() {
        return isSeen;
    }

    public void setSeen(Boolean seen) {
        isSeen = seen;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
