package com.example.demo.bean;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.demo.model.Usuario;
import com.example.demo.model.UsuarioRol;
import com.example.demo.service.PracticaSvc;

import jakarta.faces.context.FacesContext;

@Component("loginBean")
@Scope("view")
public class LoginBean extends BaseBean {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(LoginBean.class);

	@Autowired
	private PracticaSvc practicaSvc;

	private String username;
	private String password;

	public String login() {
		Usuario usuario = practicaSvc.buscarPorCorreo(username);
		if (usuario != null && usuario.getContrasenia().equals(password)) {
			UsuarioRol userRol = practicaSvc.findByUsuarioCorreo(username);
			if (userRol != null) {
				// Guardar usuario y rol en sesión
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentUser", usuario);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userRole", userRol.getRol().getNombre());
				
				logger.info("El Usuario: {} con Username: {} y Rol: {} ha iniciado sesión", 
					usuario.getNombreCompleto(), 
					usuario.getCorreo(),
					userRol.getRol().getNombre());
				
				addInfoMessage("Bienvenido", usuario.getNombreCompleto());
				
				try {
                    String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
                    String targetPage = "ADMIN".equals(userRol.getRol().getNombre()) ? 
                        contextPath + "/pages/home-admin.xhtml" : 
                        contextPath + "/pages/home-user.xhtml";
                    FacesContext.getCurrentInstance().getExternalContext().redirect(targetPage);
                    return null;
                } catch (IOException e) {
                    logger.error("Error al redirigir después del login: {}", e.getMessage());
                    addErrorMessage("Error", "Error al redirigir después del login");
                    return null;
                }
			}
		}
		
		logger.warn("Intento fallido de inicio de sesión para usuario: {}", username);
		addErrorMessage("Error de autenticación", "Credenciales inválidas");
		return null;
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
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
		Object currentUser = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser");
		if (currentUser != null) {
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
								+ "/pages/ping.xhtml");
			} catch (IOException e) {
				logger.error("Error al redirigir: {}", e.getMessage());
				addErrorMessage("Error", "Error al redirigir a ping");
			}
		} else {
			logger.warn("Intento de acceso sin autenticación");
			addErrorMessage("Error", "Debe iniciar sesión primero");
		}
	}
}
