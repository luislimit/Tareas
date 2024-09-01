/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.service;

import es.luisev.tareas.exception.TareasApplicationException;
import es.luisev.tareas.model.DBEntity;
import es.luisev.tareas.model.Filtro;
import es.luisev.tareas.model.Peticion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import es.luisev.tareas.repository.PeticionRepository;
import es.luisev.tareas.utils.Constantes;
import es.luisev.tareas.utils.UIHelper;
import java.util.Date;

/**
 *
 * @author Luis-Enrique.Varona
 */
@Service(Constantes.SERVICIO_PETICION)
public class PeticionService extends BaseService {

    @Autowired
    private PeticionRepository repository;

    public List<Peticion> findAll() {
        return repository.findAll();
    }

    public List<Peticion> findByCriteria(Filtro criterio) {
        /*List<Peticion> lista =repository.findAll();
        if (criterio == null) {
            return lista;
        }
        Long idCategoria = criterio.getCategoria() == null? criterio.getCategoria().getId(): null;
        Long idSubCategoria = criterio.getSubCategoria() == null? criterio.getSubCategoria().getId(): null;
        return lista.stream().filter(
                p -> ((criterio.getAsuntoContiene().isEmpty()
                || (idCategoria != null && p.getCategoria().getId().equals(idCategoria))
                || (idSubCategoria != null && p.getSubCategoria().getId().equals(idSubCategoria))
                || (p.getCodigo() != null && p.getCodigo().toUpperCase().contains(criterio.getAsuntoContiene().toUpperCase()))
                || (p.getAsunto() != null && p.getAsunto().toUpperCase().contains(criterio.getAsuntoContiene().toUpperCase()))
                || (p.getDescripcion() != null && p.getDescripcion().toUpperCase().contains(criterio.getAsuntoContiene().toUpperCase())))
                && (isInRange(p.getHorasReal(), criterio.getHorasRealesDesde(), criterio.getHorasRealesHasta()))
                && (isInRange(p.getPorcentaje(), criterio.getPorcentajeRealizacionDesde(), criterio.getPorcentajeRealizacionHasta())))
        ).collect(Collectors.toList());*/
        
        Long idCategoria = DBEntity.getPK(criterio.getCategoria());
        Long idSubCategoria = DBEntity.getPK(criterio.getSubCategoria());
        Long idPeticion = DBEntity.getPK(criterio.getPeticion());
        Long idEstado = DBEntity.getPK(criterio.getEstado());
        String asuntoContiene = criterio.getAsuntoContiene();
        asuntoContiene = (asuntoContiene == null)? null: "%"+asuntoContiene +"%";

        return repository.findByCriteria(
                idCategoria, 
                idSubCategoria, 
                idPeticion, 
                idEstado, 
                asuntoContiene,
                criterio.getInicioPrevistoDesde(),
                criterio.getInicioPrevistoHasta(),
                criterio.getInicioRealDesde(),
                criterio.getInicioRealHasta(),
                criterio.getFinPrevistoDesde(),
                criterio.getFinPrevistoHasta(),
                criterio.getFinRealDesde(),
                criterio.getFinRealHasta(), 
                criterio.getHorasPrevistaDesde(),
                criterio.getHorasPrevistaHasta(),
                criterio.getHorasRealesDesde(),
                criterio.getHorasRealesHasta(),
                criterio.getPorcentajeDesde(),
                criterio.getPorcentajeHasta(),
                //
                criterio.getImputacionDesde(),
                criterio.getImputacionHasta(),
                criterio.getHorasImputadasDesde(),
                criterio.getHorasImputadasHasta()
        );
    }

    public Peticion insert(Peticion peticion) throws TareasApplicationException {
        validate(peticion);
        Date ahora = new Date();
        peticion.setFecAlta(ahora.getTime());
        return repository.save(peticion);
    }

    public Optional<Peticion> findById(Long id) {
        return repository.findById(id);
    }

    public Peticion findByCodigo(Peticion peticion) {
        if (peticion == null) {
            return null;
        }
        List<Peticion> peticiones = repository.findByCodigo(peticion.getCodigo());
        if (peticiones != null && peticiones.size() > 0) {
            return peticiones.get(0);
        }
        return null;
    }

    public Peticion update(Peticion peticion) throws TareasApplicationException {
        validate(peticion);
        return repository.save(peticion);
    }

    private void validate(Peticion peticion) throws TareasApplicationException {
        if (peticion.getCodigo().isEmpty()) {
            TareasApplicationException.raise(UIHelper.getLiteral("error.peticion.codigo.vacio"));
        }
        if (peticion.getCategoria() == null) {
            TareasApplicationException.raise(UIHelper.getLiteral("error.categoria.vacio"));
        }
        if (peticion.getSubCategoria() == null) {
            TareasApplicationException.raise(UIHelper.getLiteral("error.subcategoria.vacio"));
        }
        Peticion peticionDB = findByCodigo(peticion);
        if (peticionDB != null && (peticion.getId() == null || !peticionDB.getId().equals(peticion.getId()))) {
            TareasApplicationException.raise(UIHelper.getLiteral("error.peticion.codigo.duplicado"));
        }
        if (peticion.getAsunto().isEmpty()) {
            TareasApplicationException.raise(UIHelper.getLiteral("error.peticion.asunto.vacio"));
        }
        if (peticion.getUsuario() == null) {
            TareasApplicationException.raise(UIHelper.getLiteral("error.peticion.usuario.vacio"));
        }
        if (peticion.getEstado() == null) {
            TareasApplicationException.raise(UIHelper.getLiteral("error.peticion.estado.vacio"));
        }
    }

    public void delete(Long id) throws TareasApplicationException {
        try {
            // Verificar si tiene horas o documentos en ese caso disparar error    
            repository.deleteById(id);
        } catch (Exception e) {
            TareasApplicationException.raise(UIHelper.getLiteral("error.peticion.borrar", e.getMessage()));
        }
    }

    public List<Peticion> findByCategoriaSubCategoria(Long idCategoria, Long idSubCategoria) {
        return repository.findByCategoriaSubCategoria(idCategoria, idSubCategoria);
    }

}
