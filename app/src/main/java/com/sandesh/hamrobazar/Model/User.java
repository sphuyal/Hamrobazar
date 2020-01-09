package com.sandesh.hamrobazar.Model;

public class User {
    private String fullName;
    private String email;
    private String address1;
    private String address2;
    private String address3;
    private String phone;
    private String mobilePhone;
    private String password;
    private String image;

    public User(String fullName, String email, String address1, String address2, String address3, String phone, String mobilePhone, String password, String image) {
        this.fullName = fullName;
        this.email = email;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.phone = phone;
        this.mobilePhone = mobilePhone;
        this.password = password;
        this.image = image;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
