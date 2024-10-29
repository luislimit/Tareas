/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.service;

import es.luisev.tareas.exception.TareasApplicationException;
import es.luisev.tareas.model.Documento;
import es.luisev.tareas.model.Filtro;
import es.luisev.tareas.model.Peticion;
import es.luisev.tareas.model.TipoDocumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import es.luisev.tareas.repository.DocumentoRepository;
import static es.luisev.tareas.service.ServiceSupport.getLikeString;
import es.luisev.tareas.utils.AppHelper;
import es.luisev.tareas.utils.Constantes;
import es.luisev.tareas.utils.UIHelper;
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
    
    public List<Documento> findByCriteria(List<Peticion> peticiones, Filtro criterio) {
        List<Documento> lista = null;
        if (peticiones == null || !peticiones.isEmpty()) {
            TipoDocumento tipoDocumento = criterio.getTipoDocumento();
            //
            lista = repository.findByCriteria(
                    peticiones,
                    getLikeString(criterio.getImputacionContiene()),
                    getLikeString(criterio.getRuta()),
                    tipoDocumento == null ? null : tipoDocumento.getId()
            );
        }
        return lista;
    }    

    public Documento insert(Documento documento) throws TareasApplicationException{
        validate(documento);
        documento.setFecAlta(AppHelper.getFechaAltaBd());
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
    
    
    private void validate(Documento documento) throws TareasApplicationException {
        if (documento.getFichero() == null) {
            TareasApplicationException.raise(UIHelper.getLiteral("error.documento.fichero.vacio"));
        }
        if (documento.getTipoDocumento()== null) {
            TareasApplicationException.raise(UIHelper.getLiteral("error.documento.tipo.vacio"));
        }
        
    }    
}