package org.lld.locker.models;

import org.lld.locker.generator.IdGenerator;

import java.util.UUID;

public class Customer {
    private final String id;
    private final String name;
    private final String phoneNo;
    private final String email;

    public Customer(String name, String phoneNo, String email) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.phoneNo = phoneNo;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getEmail() {
        return email;
    }
}
