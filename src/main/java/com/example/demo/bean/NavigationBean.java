package com.example.demo.bean;

import java.io.Serializable;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class NavigationBean implements Serializable {
    
    private static final long serialVersionUID = 1L;

    public String redirectToLogin() {
        return "/pages/login.xhtml?faces-redirect=true";
    }
}
