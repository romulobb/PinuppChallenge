package com.error;

public class ClientDateInvalid extends RuntimeException {

    public ClientDateInvalid() {
        super("Date Invalid, requested format yyyy-MM-dd\" : ");
    }

}
