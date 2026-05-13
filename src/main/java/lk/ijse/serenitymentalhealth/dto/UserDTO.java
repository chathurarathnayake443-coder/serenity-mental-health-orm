package lk.ijse.serenitymentalhealth.dto;

import lk.ijse.serenitymentalhealth.enums.UserType;

public class UserDTO {
    private String username;
    private String name;
    private String password;
    private String contact;
    private String address;
    private UserType userType;

    public UserDTO() {}

    public UserDTO(String username, String name, String password, String contact, String address, UserType userType) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.contact = contact;
        this.address = address;
        this.userType = userType;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public UserType getUserType() {
        return userType;
    }
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
