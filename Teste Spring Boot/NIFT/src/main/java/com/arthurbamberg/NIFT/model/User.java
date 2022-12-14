package com.arthurbamberg.NIFT.model;

public class User {
	private  int idUser;
    private String name, email, password;
    private boolean isValid, successfullyRecorded = false;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSuccessfullyRecorded() {
        return this.successfullyRecorded;
    }
    public void setSuccessfullyRecorded(boolean successfullyRecorded) {
        this.successfullyRecorded = successfullyRecorded;
    }

    public int getIdUser() {
        return this.idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public boolean isIsValid() {
        return this.isValid;
    }

    public boolean getIsValid() {
        return this.isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public boolean getSuccessfullyRecorded() {
        return successfullyRecorded;
    }
}
