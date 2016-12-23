package com.ymatou.alarmcenter.domain.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import java.util.List;

/**
 * Created by zhangxiaoming on 2016/12/14.
 */
@Entity(value = "Operator", noClassnameStored = true)
public class Operator {
    @Id
    @Property("Id")
    private String id;


    @Property("LoginId")
    private String loginId;

    @Property("Name")
    private String name;

    @Property("Password")
    private String password;

    @Property("Email")
    private String email;

    @Property("Roles")
    private List<String> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
