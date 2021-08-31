package com.example.cred_operation_sql.SQL;

public class Data {
    String userName , email , number;

    public Data(String userName, String email, String number) {
        this.userName = userName;
        this.email = email;
        this.number = number;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }
}
