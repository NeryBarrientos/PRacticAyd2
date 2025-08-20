package com.example.demo.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;

public class AuthorizationListener implements PhaseListener {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationListener.class);
    
    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext facesContext = event.getFacesContext();
        
        // Si no hay ViewRoot, es probablemente una petición inicial o una redirección
        if (facesContext.getViewRoot() == null) {
            return;
        }
        
        String currentPage = facesContext.getViewRoot().getViewId();
        logger.debug("Página actual: {}", currentPage);
        
        // Verificar si es una petición de recursos
        if (currentPage.contains("/jakarta.faces.resource/")) {
            return;
        }
        
        // Páginas públicas (no requieren autenticación)
        if (currentPage.contains("login.xhtml")) {
            // Si el usuario ya está autenticado y trata de acceder a login, redirigir a su página de inicio
            Object currentUser = facesContext.getExternalContext().getSessionMap().get("currentUser");
            if (currentUser != null) {
                String userRole = (String) facesContext.getExternalContext().getSessionMap().get("userRole");
                redirectToHomePage(facesContext, userRole);
            }
            return;
        }
        
        // Para todas las demás páginas, verificar autenticación
        Object currentUser = facesContext.getExternalContext().getSessionMap().get("currentUser");
        String userRole = (String) facesContext.getExternalContext().getSessionMap().get("userRole");
        
        // Si no hay usuario logueado, redirigir a login
        if (currentUser == null) {
            logger.warn("Intento de acceso sin autenticación a: {}", currentPage);
            handleUnauthorizedAccess(facesContext, "Debe iniciar sesión primero");
            return;
        }
        
        // Verificar permisos basados en roles
        if (currentPage.contains("home-admin.xhtml") && !"ADMIN".equals(userRole)) {
            logger.warn("Usuario con rol {} intentó acceder a página de admin", userRole);
            handleUnauthorizedAccess(facesContext, "No tiene permiso para acceder a esta página");
            return;
        }
        
        if (currentPage.contains("home-user.xhtml") && !"USER".equals(userRole)) {
            logger.warn("Usuario con rol {} intentó acceder a página de usuario", userRole);
            handleUnauthorizedAccess(facesContext, "No tiene permiso para acceder a esta página");
            return;
        }
        
        // Redireccionar a la página de inicio si está en la raíz o index
        if (currentPage.equals("/") || currentPage.equals("/index.xhtml")) {
            redirectToHomePage(facesContext, userRole);
        }
    }
    
    private void redirectToHomePage(FacesContext facesContext, String userRole) {
        try {
            String contextPath = facesContext.getExternalContext().getRequestContextPath();
            String targetPage = "ADMIN".equals(userRole) ? "/pages/home-admin.xhtml" : "/pages/home-user.xhtml";
            logger.info("Redirigiendo a página de inicio: {}", targetPage);
            facesContext.getExternalContext().redirect(contextPath + targetPage);
        } catch (IOException e) {
            logger.error("Error redirigiendo a página de inicio: {}", e.getMessage());
        }
    }

    private void handleUnauthorizedAccess(FacesContext facesContext, String message) {
        try {
            facesContext.getExternalContext().getSessionMap().put("errorMessage", message);
            String contextPath = facesContext.getExternalContext().getRequestContextPath();
            facesContext.getExternalContext().redirect(contextPath + "/pages/login.xhtml");
        } catch (IOException e) {
            logger.error("Error redirigiendo a login: {}", e.getMessage());
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
