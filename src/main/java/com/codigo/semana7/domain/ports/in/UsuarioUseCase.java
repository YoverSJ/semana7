package com.codigo.semana7.domain.ports.in;

import com.codigo.semana7.domain.model.Usuario;

import java.util.Optional;

public interface UsuarioUseCase {

    Usuario crearUsuario(Usuario usuario);

    Optional<Usuario> getUsuarioById(Long id);

    Optional<Usuario> actualizarUsuario(Usuario usuario);

    boolean eliminarUsuarioById(Long id);
}
