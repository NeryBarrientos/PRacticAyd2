package com.example.demo.bean;

import java.io.Serializable;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

public abstract class BaseBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    protected void addMessage(String summary) {
        addMessage(summary, null);
    }
    
    protected void addMessage(String summary, String detail) {
        FacesContext.getCurrentInstance()
                   .addMessage(null, new FacesMessage(summary, detail));
    }
    
    protected void addErrorMessage(String summary) {
        addErrorMessage(summary, null);
    }
    
    protected void addErrorMessage(String summary, String detail) {
        FacesContext.getCurrentInstance()
                   .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
    }
    
    protected void addInfoMessage(String summary) {
        addInfoMessage(summary, null);
    }
    
    protected void addInfoMessage(String summary, String detail) {
        FacesContext.getCurrentInstance()
                   .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
    }
}
