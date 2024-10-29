/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.service;

import es.luisev.tareas.model.TipoDocumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import es.luisev.tareas.repository.TipoDocumentoRepository;
import es.luisev.tareas.utils.Constantes;

/**
 *
 * @author Luis-Enrique.Varona
 */
@Service(Constantes.SERVICIO_TIPO_DOCUMENTO)
public class TipoDocumentoService {

    @Autowired
    private TipoDocumentoRepository repository;

    public List<TipoDocumento> findAll() {
        return repository.findAll();
    }

    public TipoDocumento insert(TipoDocumento tipoDocumento) {
        return repository.save(tipoDocumento);
    }

    public Optional<TipoDocumento> findById(Long id) {
        return repository.findById(id);
    }

    public TipoDocumento update(Long id, TipoDocumento tipoDocumentoDetails) {
        TipoDocumento tipoDocumento = repository.findById(id).orElse(null);
        if (tipoDocumento != null) {
            return repository.save(tipoDocumentoDetails);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
