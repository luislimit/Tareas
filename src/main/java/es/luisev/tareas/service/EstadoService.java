/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.service;

import es.luisev.tareas.model.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import es.luisev.tareas.repository.EstadoRepository;
import es.luisev.tareas.utils.Constantes;

/**
 *
 * @author Luis-Enrique.Varona
 */
@Service(Constantes.SERVICIO_ESTADO)
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    public List<Estado> findAll() {
        return repository.findAll();
    }

    public Estado insert(Estado estado) {
        return repository.save(estado);
    }

    public Optional<Estado> findById(Long id) {
        return repository.findById(id);
    }

    public Estado update(Estado estadoDetails) {
        return repository.save(estadoDetails);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
