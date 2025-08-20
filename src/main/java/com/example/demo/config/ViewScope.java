package com.example.demo.config;

import java.util.Map;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import jakarta.faces.context.FacesContext;

public class ViewScope implements Scope {

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext == null) {
            throw new IllegalStateException("FacesContext.getCurrentInstance() returned null");
        }
        
        Map<String, Object> viewMap = facesContext.getViewRoot().getViewMap();
        Object bean = viewMap.get(name);
        
        if (bean == null) {
            bean = objectFactory.getObject();
            viewMap.put(name, bean);
        }
        
        return bean;
    }

    @Override
    public Object remove(String name) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
            return facesContext.getViewRoot().getViewMap().remove(name);
        }
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        // Not supported
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
