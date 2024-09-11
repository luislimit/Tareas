/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.service;

import es.luisev.tareas.exception.TareasApplicationException;
import es.luisev.tareas.model.Filtro;
import es.luisev.tareas.model.Imputacion;
import es.luisev.tareas.model.Peticion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import es.luisev.tareas.repository.ImputacionRepository;
import es.luisev.tareas.utils.AppHelper;
import es.luisev.tareas.utils.Constantes;
import es.luisev.tareas.utils.UIHelper;
import java.util.Date;

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

    public List<Imputacion> findByCriteria(List<Peticion> peticiones, Filtro criterio) {
        List<Imputacion> lista = null;
        String extra;
        switch (criterio.getTipoHoras()) {
            case 1 -> {
                extra = "N"; // Horas normales
            }
            case 2 -> {
                extra = "S"; // Horas extra
            }
            default -> {
                extra = null; // Todas
            }
        }
        if (peticiones == null || !peticiones.isEmpty()) {
            //
            lista = repository.findByCriteria(
                    peticiones,
                    criterio.getImputacionDesde(),
                    criterio.getImputacionHasta(),
                    criterio.getHorasImputadasDesde(),
                    criterio.getHorasImputadasHasta(),
                    extra);
        }
        return lista;
    }

    public Imputacion insert(Imputacion imputacion) throws TareasApplicationException {
        validate(imputacion);
        imputacion.setFecAlta(AppHelper.getFechaAltaBd());
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
