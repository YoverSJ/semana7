package com.codigo.semana7.domain.ports.out;

import com.codigo.semana7.domain.model.Persona;

import java.util.Optional;

public interface PersonaRepositoryPort {

    Persona save(Persona persona);

    Optional<Persona> findById(Long id);

    Persona update(Persona persona);

    Boolean delete(Long id);

}
