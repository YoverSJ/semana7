package com.codigo.semana7.infraestructure.repository;

import com.codigo.semana7.domain.model.Usuario;
import com.codigo.semana7.infraestructure.entity.PersonaEntity;
import com.codigo.semana7.infraestructure.entity.UsuarioEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioJpaRepositoryAdapterTest {

    @Mock
    UsuarioJpaRepository usuarioJpaRepository;

    @InjectMocks
    UsuarioJpaRepositoryAdapter usuarioJpaRepositoryAdapter;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        usuarioJpaRepositoryAdapter = new UsuarioJpaRepositoryAdapter(usuarioJpaRepository);
    }

    @Test
    void save() {

        PersonaEntity persona = new PersonaEntity(1L,"Yover","Santiago", new Date(),"Masculino");

        Usuario usuario = new Usuario(1L, "ysantiago", "123456", "ysantiago@domain.com", persona);

        UsuarioEntity usuarioEntity = new UsuarioEntity(1L, "ysantiago", "123456", "ysantiago@domain.com", persona);

        Mockito.when(usuarioJpaRepository.save(Mockito.any(UsuarioEntity.class))).thenReturn(usuarioEntity);

        Usuario responseUsuario = usuarioJpaRepositoryAdapter.save(usuario);

        assertNotNull(responseUsuario);
        assertEquals(usuario.getId(), responseUsuario.getId());
        assertEquals(usuario.getNombreUsuario(), responseUsuario.getNombreUsuario());
        assertEquals(persona.getId(), responseUsuario.getPersona().getId());
        assertEquals(persona.getNombre(), responseUsuario.getPersona().getNombre());

    }

    @Test
    void findById() {

        Long id = 1L;

        PersonaEntity persona = new PersonaEntity(1L,"Yover","Santiago", new Date(),"Masculino");

        UsuarioEntity usuarioEntity = new UsuarioEntity(1L, "ysantiago", "123456", "ysantiago@domain.com", persona);

        Mockito.when(usuarioJpaRepository.findById(id)).thenReturn(Optional.of(usuarioEntity));

        Optional<Usuario> usuarioOptional = usuarioJpaRepositoryAdapter.findById(id);

        assertNotNull(usuarioOptional);

        usuarioOptional.map(usuario -> {
           assertEquals(usuarioEntity.getId(), usuario.getId());
           assertEquals(usuarioEntity.getNombreUsuario(), usuario.getNombreUsuario());
            assertEquals(persona.getId(), usuario.getPersona().getId());
            assertEquals(persona.getNombre(), usuario.getPersona().getNombre());
           return true;
        });

    }

    @Test
    void updateExitoso() {

        PersonaEntity persona = new PersonaEntity(1L,"Yover","Santiago", new Date(),"Masculino");

        Usuario usuario = new Usuario(1L, "ysantiago", "123456", "ysantiago@domain.com", persona);

        UsuarioEntity usuarioEntity = new UsuarioEntity(1L, "ysantiago", "123456", "ysantiago@domain.com", persona);

        Mockito.when(usuarioJpaRepository.existsById(usuario.getId())).thenReturn(true);
        Mockito.when(usuarioJpaRepository.save(Mockito.any(UsuarioEntity.class))).thenReturn(usuarioEntity);

        Optional<Usuario> responseUsuario = usuarioJpaRepositoryAdapter.update(usuario);

        assertNotNull(responseUsuario);

        responseUsuario.map(usuario1 -> {

            assertEquals(usuario.getId(), usuario1.getId());
            assertEquals(usuario.getNombreUsuario(), usuario1.getNombreUsuario());
            assertEquals(persona.getId(), usuario1.getPersona().getId());
            assertEquals(persona.getNombre(), usuario1.getPersona().getNombre());
            return true;

        });


    }

    @Test
    void updateNull(){

        Usuario usuario = new Usuario();

        Mockito.when(usuarioJpaRepository.existsById(usuario.getId())).thenReturn(false);

        Optional<Usuario> usuarioOptional = usuarioJpaRepositoryAdapter.update(usuario);

        assertTrue(usuarioOptional.isEmpty());

    }

    @Test
    void deleteUsuarioByIdExitoso() {

        Long id = 1L;

        Mockito.when(usuarioJpaRepository.existsById(id)).thenReturn(true);

        boolean response = usuarioJpaRepositoryAdapter.deleteUsuarioById(id);

        assertTrue(response);
    }

    @Test
    void deleteUsuarioByIdFalse(){

        Long id = 1L;

        Mockito.when(usuarioJpaRepository.existsById(id)).thenReturn(false);

        boolean response = usuarioJpaRepositoryAdapter.deleteUsuarioById(id);

        assertFalse(response);

    }
}