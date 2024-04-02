package com.nguyenthithao.model;

public class Contact {
    private String contactId;
    private String name;
    private String phone;
    private String email;

    public Contact() {
    }

    public Contact(String contactId, String name, String phone, String email) {
        this.contactId = contactId;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }


    public String getContactId() {
        return contactId;
    }

    public String getPhone() {
        return phone;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

