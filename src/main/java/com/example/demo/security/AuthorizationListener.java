package com.example.demo.security;

import java.io.IOException;
import java.util.logging.Logger;

import jakarta.faces.application.NavigationHandler;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;

public class AuthorizationListener implements PhaseListener {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(AuthorizationListener.class.getName());
    
    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext facesContext = event.getFacesContext();
        
        // Si no hay ViewRoot, es probablemente una petición inicial
        if (facesContext.getViewRoot() == null) {
            try {
                String contextPath = facesContext.getExternalContext().getRequestContextPath();
                facesContext.getExternalContext().redirect(contextPath + "/pages/login.xhtml");
                return;
            } catch (IOException e) {
                logger.severe("Error redirigiendo a login: " + e.getMessage());
            }
            return;
        }
        
        String currentPage = facesContext.getViewRoot().getViewId();
        logger.info("Página actual: " + currentPage);
        
        // Redireccionar a login si es la raíz
        if (currentPage.equals("/")) {
            try {
                String contextPath = facesContext.getExternalContext().getRequestContextPath();
                facesContext.getExternalContext().redirect(contextPath + "/pages/login.xhtml");
                return;
            } catch (IOException e) {
                logger.severe("Error al redirigir a login: " + e.getMessage());
            }
        }
        
        boolean isLoginPage = currentPage.lastIndexOf("login.xhtml") > -1;
        boolean isResourceRequest = currentPage.contains("/jakarta.faces.resource/");
        
        if (isResourceRequest) {
            return;
        }
        
        Object currentUser = facesContext.getExternalContext()
                                       .getSessionMap()
                                       .get("currentUser");
        
        if (!isLoginPage && currentUser == null) {
            logger.info("Unauthorized access attempt to " + currentPage);
            NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
            nh.handleNavigation(facesContext, null, "/pages/login?faces-redirect=true");
        }
    }
    
    @Override
    public void beforePhase(PhaseEvent event) {
        // No necesitamos hacer nada antes de la fase
    }
    
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
