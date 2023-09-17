package com.codigo.semana7.domain.ports.out;

import com.codigo.semana7.domain.model.Usuario;

import java.util.Optional;

public interface UsuarioRepositoryPort {

    Usuario save(Usuario usuario);

    Optional<Usuario> findById(Long id);

    Optional<Usuario> update(Usuario usuario);

    boolean deleteUsuarioById(Long id);
}
