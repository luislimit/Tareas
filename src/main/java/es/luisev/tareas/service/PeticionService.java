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
import es.luisev.tareas.utils.AppHelper;
import es.luisev.tareas.utils.Constantes;
import es.luisev.tareas.utils.UIHelper;

/**
 *
 * @author Luis-Enrique.Varona
 */
@Service(Constantes.SERVICIO_PETICION)
public class PeticionService extends ServiceSupport{

    @Autowired
    private PeticionRepository repository;

    public List<Peticion> findAll() {
        return repository.findAll();
    }

    public List<Peticion> findByCriteria(Filtro filtro) {
        Long idCategoria = DBEntity.getPK(filtro.getCategoria());
        Long idSubCategoria = DBEntity.getPK(filtro.getSubCategoria());
       // Long idPeticion = DBEntity.getPK(filtro.getPeticion());
        Long idEstado = DBEntity.getPK(filtro.getEstado());

        String extra;
        switch (filtro.getTipoHoras()) {
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

        return repository.findByCriteria(idCategoria,
                idSubCategoria,
                idEstado,
                getLikeString(filtro.getAsuntoContiene()),
                filtro.getInicioPrevistoDesde(),
                filtro.getInicioPrevistoHasta(),
                filtro.getInicioRealDesde(),
                filtro.getInicioRealHasta(),
                filtro.getFinPrevistoDesde(),
                filtro.getFinPrevistoHasta(),
                filtro.getFinRealDesde(),
                filtro.getFinRealHasta(),
                filtro.getHorasPrevistaDesde(),
                filtro.getHorasPrevistaHasta(),
                filtro.getHorasRealesDesde(),
                filtro.getHorasRealesHasta(),
                filtro.getPorcentajeDesde(),
                filtro.getPorcentajeHasta(),
                filtro.getTipoListado() // Tipo de listado
                ); 
    }
    
    /**
     * Retorna la suma de las horas imputadas al peticion
     * @param id
     * @return 
     */
    public Double sumHorasImputadas(Long id){
        return repository.sumHorasImputadas(id);
    }

    public Peticion insert(Peticion peticion) throws TareasApplicationException {
        validate(peticion);
        peticion.setFecAlta(AppHelper.getFechaAltaBd());
        return repository.save(peticion);
    }

    public Optional<Peticion> findById(Long id) {
        return repository.findById(id);
    }

    public Peticion findByCodigo(String codigo) {
        if (codigo == null) {
            return null;
        }
        List<Peticion> peticiones = repository.findByCodigo(codigo);
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
        Peticion peticionDB = findByCodigo(peticion.getCodigo());
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
