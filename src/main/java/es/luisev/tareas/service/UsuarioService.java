/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.service;

import es.luisev.tareas.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import es.luisev.tareas.repository.UsuarioRepository;
import es.luisev.tareas.utils.Constantes;

/**
 *
 * @author Luis-Enrique.Varona
 */
@Service(Constantes.SERVICIO_USUARIO)
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario insert(Usuario user) {
        return repository.save(user);
    }

    public Optional<Usuario> findById(Long id) {
        return repository.findById(id);
    }

    public Usuario findByCodigo(String codigo) {
        List<Usuario> usuarios = repository.findAll();
        for (Usuario usuario: usuarios){
            if (usuario.getCodigo().equals(codigo)){
                return usuario;
            }
        }
        return null;
    }
    
    public Usuario update(Long id, Usuario userDetails) {
        Usuario user = repository.findById(id).orElse(null);
        if (user != null) {
            user.setCodigo(userDetails.getCodigo());
            user.setEmail(userDetails.getEmail());
            return repository.save(user);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}