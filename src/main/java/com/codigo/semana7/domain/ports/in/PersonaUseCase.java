package com.codigo.semana7.domain.ports.in;

import com.codigo.semana7.domain.model.Persona;

import java.util.Optional;

public interface PersonaUseCase {

    Persona crearPersona(Persona persona);

    Optional<Persona> getPersona(Long id);

    Persona actualizarPersona(Persona persona);

    Boolean eliminarPersona(Long id);

}
