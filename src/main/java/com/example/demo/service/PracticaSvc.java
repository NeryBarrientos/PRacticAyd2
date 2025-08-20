package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.model.UsuarioRol;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.UsuarioRolRepository;

@Service
public class PracticaSvc {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioRolRepository usuarioRolRepository;

	public Usuario buscarPorCorreo(String correo) {
		Usuario usuario = usuarioRepository.findByCorreo(correo);
		return usuario;
	}

	public UsuarioRol findByUsuarioCorreo(String correo) {
		UsuarioRol userRol = usuarioRolRepository.findByUsuarioCorreo(correo);
		if (userRol != null) {
			if (userRol.getRol() != null) {
				userRol.getRol().getIdRol();
			}
			if (userRol.getUsuario() != null) {
				userRol.getUsuario().getIdUsuario();
			}
		}
		return userRol;
	}

	/**
	 * Guarda o actualiza un usuario
	 * 
	 * @param usuario El usuario a guardar
	 * @return El usuario guardado
	 */
	public Usuario guardarUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	/**
	 * Elimina un usuario
	 * 
	 * @param usuario El usuario a eliminar
	 */
	public void eliminarUsuario(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}

	/**
	 * Busca un usuario por su ID
	 * 
	 * @param id El ID del usuario
	 * @return El usuario encontrado o null si no existe
	 */
	public Usuario buscarPorId(Long id) {
		return usuarioRepository.findById(id).orElse(null);
	}
}
