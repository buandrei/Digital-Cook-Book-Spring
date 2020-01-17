package com.digitalcookbook.dto;

import com.digitalcookbook.domain.AuthProvider;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class UserDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Boolean emailVerified = false;

    @JsonIgnore
    private String password;

    private AuthProvider provider;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    private UserDTO id(Long id){
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDTO firstName(String firstName){
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDTO lastName(String lastName){
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO email(String email){
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }
    public UserDTO emailVerified(Boolean emailVerified){
        this.emailVerified = emailVerified;
        return this;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO password(String password){
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public UserDTO provider(AuthProvider provider){
        this.provider = provider;
        return this;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

}
