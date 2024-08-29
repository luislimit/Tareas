/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.service;

import es.luisev.tareas.model.Documento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import es.luisev.tareas.repository.DocumentoRepository;
import es.luisev.tareas.utils.Constantes;
/**
 *
 * @author Luis-Enrique.Varona
 */
@Service(Constantes.SERVICIO_DOCUMENTO)
public class DocumentoService {

    @Autowired
    private DocumentoRepository repository;
    
    public List<Documento> findAll() {
        return repository.findAll();
    }

    public Documento insert(Documento documento) {
        return repository.save(documento);
    }

    public Optional<Documento> findById(Long id) {
        return repository.findById(id);
    }

    public Documento update(Documento documentoDetails) {
       return repository.save(documentoDetails);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }    
}