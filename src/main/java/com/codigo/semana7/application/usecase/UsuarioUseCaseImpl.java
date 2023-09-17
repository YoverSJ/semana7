package com.codigo.semana7.application.usecase;

import com.codigo.semana7.domain.model.Usuario;
import com.codigo.semana7.domain.ports.in.UsuarioUseCase;
import com.codigo.semana7.domain.ports.out.UsuarioRepositoryPort;

import java.util.Optional;

public class UsuarioUseCaseImpl implements UsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public UsuarioUseCaseImpl(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepositoryPort.save(usuario);
    }

    @Override
    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepositoryPort.findById(id);
    }

    @Override
    public Optional<Usuario> actualizarUsuario(Usuario usuario) {
        return usuarioRepositoryPort.update(usuario);
    }

    @Override
    public boolean eliminarUsuarioById(Long id) {
        return usuarioRepositoryPort.deleteUsuarioById(id);
    }
}
