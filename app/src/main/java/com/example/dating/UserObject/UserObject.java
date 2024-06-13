package com.example.dating.UserObject;

import java.io.Serializable;

public class UserObject implements Serializable {
    private String uid, name, phone, notificationKey;
    private Boolean selected = false;

    public UserObject(String uid) {
        this.uid = uid;
    }

    public UserObject(String name, String phone, String notificationKey) {
        this.name = name;
        this.phone = phone;
        this.notificationKey = notificationKey;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNotificationKey() {
        return notificationKey;
    }

    public void setNotificationKey(String notificationKey) {
        this.notificationKey = notificationKey;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
