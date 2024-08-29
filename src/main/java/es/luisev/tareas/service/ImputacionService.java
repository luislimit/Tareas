/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.service;

import es.luisev.tareas.exception.TareasApplicationException;
import es.luisev.tareas.model.Imputacion;
import es.luisev.tareas.model.Peticion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import es.luisev.tareas.repository.ImputacionRepository;
import es.luisev.tareas.utils.Constantes;
import es.luisev.tareas.utils.UIHelper;
import java.util.Date;
import java.util.stream.Collectors;

/**
 *
 * @author Luis-Enrique.Varona
 */
@Service(Constantes.SERVICIO_IMPUTACION)
public class ImputacionService extends BaseService {

    @Autowired
    private ImputacionRepository repository;

    public List<Imputacion> findAll() {
        return repository.findAll();
    }

    public List<Imputacion> findByPeticiones(List<Peticion> peticiones) {
        /*if (peticiones == null || peticiones.isEmpty()){
            return null;
        }
        List<Imputacion> lista = repository.findAll();
        return lista.stream().filter(
                p -> (contiene(peticiones, p.getPeticion()))//criterio.getPeticiones().contains(p.getPeticion())
        ).collect(Collectors.toList());*/
        List<Imputacion> lista = null;
        if (peticiones == null || !peticiones.isEmpty()) {
           lista= repository.findByPeticiones(peticiones);
        }
        return lista;
    }

    private boolean contiene(List<Peticion> peticiones, Peticion peticion) {
        for (Peticion p : peticiones) {
            if (p.getId().equals(peticion.getId())) {
                return true;
            }
        }
        return false;
    }

    public Imputacion insert(Imputacion imputacion) throws TareasApplicationException {
        validate(imputacion);
        Date ahora = new Date();
        imputacion.setFecAlta(ahora.getTime());
        if (imputacion.getEstado() == null) {
            imputacion.setEstado(imputacion.getPeticion().getEstado());
        }
        if (imputacion.getEstadoPrevio() == null) {
            imputacion.setEstadoPrevio(imputacion.getPeticion().getEstado());
        }
        return repository.save(imputacion);
    }

    public Optional<Imputacion> findById(Long id) {
        return repository.findById(id);
    }

    public Imputacion update(Imputacion imputacion) throws TareasApplicationException {
        validate(imputacion);
        return repository.save(imputacion);
    }

    public void delete(Long id) throws TareasApplicationException {
        try {
            // Verificar si tiene horas o documentos en ese caso disparar error    
            repository.deleteById(id);
        } catch (Exception e) {
            TareasApplicationException.raise(UIHelper.getLiteral("error.imputacion.borrar", e.getMessage()));
        }
    }

    private void validate(Imputacion imputacion) throws TareasApplicationException {
        if (imputacion.getFecha() == null) {
            TareasApplicationException.raise(UIHelper.getLiteral("error.imputacion.fecha.vacio"));
        }
        if (imputacion.getHorasReal() == null) {
            TareasApplicationException.raise(UIHelper.getLiteral("error.imputacion.hora.vacio"));
        }
        if (imputacion.getHorasReal() < 0) {
            TareasApplicationException.raise(UIHelper.getLiteral("error.imputacion.hora.invalida"));
        }
        if (imputacion.getPeticion() == null) {
            TareasApplicationException.raise(UIHelper.getLiteral("error.peticion.vacio"));
        }
    }
}
