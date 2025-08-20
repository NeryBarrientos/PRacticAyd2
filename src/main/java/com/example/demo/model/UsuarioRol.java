package com.example.demo.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario_rol")
public class UsuarioRol implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_usuario_rol")
	private Long idRol;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario")
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rol")
	private Rol rol;

	public UsuarioRol() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsuarioRol(Long idRol, Usuario usuario, Rol rol) {
		super();
		this.idRol = idRol;
		this.usuario = usuario;
		this.rol = rol;
	}

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "UsuarioRol [idRol=" + idRol + ", usuario=" + usuario.toString() + ", rol=" + rol.toString() + "]";
	}

}