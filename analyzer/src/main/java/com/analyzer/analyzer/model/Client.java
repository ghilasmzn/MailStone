package com.analyzer.analyzer.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Client {
    private Long id;

    private String firstname, lastname, email;
    private String address, phoneNumber;

    public Client(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public void setInfos() {
        setFirstname("");
        setLastname("");
        setEmail("");
        setAddress("");
        setPhoneNumber("");
    }
}
