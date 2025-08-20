package com.example.demo.bean;

import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.faces.context.FacesContext;

@Component("loginBean")
@Scope("view")
public class LoginBean extends BaseBean {
    
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(LoginBean.class.getName());
    
    private String username;
    private String password;
    
    public String login() {
        // Aquí irá la lógica de autenticación
        if ("admin".equals(username) && "admin".equals(password)) {
            // Guardar usuario en sesión
            FacesContext.getCurrentInstance()
                       .getExternalContext()
                       .getSessionMap()
                       .put("currentUser", username);
            
            addInfoMessage("Bienvenido", username);
            return "/pages/home?faces-redirect=true";
        } else {
            addErrorMessage("Error de autenticación", "Credenciales inválidas");
            return null;
        }
    }
    
    public String logout() {
        FacesContext.getCurrentInstance()
                   .getExternalContext()
                   .invalidateSession();
        return "/pages/login?faces-redirect=true";
    }
    
    // Getters y setters
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void goToPing() {
        logger.info("Intentando ir a ping...");
        Object currentUser = FacesContext.getCurrentInstance()
                                      .getExternalContext()
                                      .getSessionMap()
                                      .get("currentUser");
        if (currentUser != null) {
            try {
                FacesContext.getCurrentInstance()
                           .getExternalContext()
                           .redirect(FacesContext.getCurrentInstance()
                                               .getExternalContext()
                                               .getRequestContextPath() + "/pages/ping.xhtml");
            } catch (IOException e) {
                logger.severe("Error al redirigir: " + e.getMessage());
                addErrorMessage("Error", "Error al redirigir a ping");
            }
        } else {
            logger.warning("Intento de acceso sin autenticación");
            addErrorMessage("Error", "Debe iniciar sesión primero");
        }
    }
}
