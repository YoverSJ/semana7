package com.codigo.semana7.infraestructure.repository;

import com.codigo.semana7.domain.model.Usuario;
import com.codigo.semana7.domain.ports.out.UsuarioRepositoryPort;
import com.codigo.semana7.infraestructure.entity.PersonaEntity;
import com.codigo.semana7.infraestructure.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioJpaRepositoryAdapter implements UsuarioRepositoryPort {

    private final UsuarioJpaRepository usuarioJpaRepository;

    public UsuarioJpaRepositoryAdapter(UsuarioJpaRepository usuarioJpaRepository) {
        this.usuarioJpaRepository = usuarioJpaRepository;
    }

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity usuarioEntity = UsuarioEntity.fromDomainModel(usuario);
        UsuarioEntity saveUsuarioEntity = usuarioJpaRepository.save(usuarioEntity);
        return saveUsuarioEntity.toDomainModel();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioJpaRepository.findById(id).map(UsuarioEntity::toDomainModel);
    }

    @Override
    public Optional<Usuario> update(Usuario usuario) {
        if (usuarioJpaRepository.existsById(usuario.getId())){
            UsuarioEntity usuarioEntity = UsuarioEntity.fromDomainModel(usuario);
            UsuarioEntity actualizarUsuarioEntity = usuarioJpaRepository.save(usuarioEntity);
            return Optional.of(actualizarUsuarioEntity.toDomainModel());
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteUsuarioById(Long id) {
        if (usuarioJpaRepository.existsById(id)){
            usuarioJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
