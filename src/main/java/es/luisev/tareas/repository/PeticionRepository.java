/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.luisev.tareas.model.Peticion;
import es.luisev.tareas.utils.Constantes;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Luis-Enrique.Varona
 */

@Repository
public interface PeticionRepository extends JpaRepository<Peticion, Long> {
    
    @Query("SELECT p FROM Peticion p WHERE (:idCategoria is null or p.categoria.id = :idCategoria) and (:idSubCategoria is null or p.subCategoria.id = :idSubCategoria)")
    List<Peticion> findByCategoriaSubCategoria(@Param("idCategoria") Long idCategoria, @Param("idSubCategoria") Long idSubCategoria);  
    
    @Query("SELECT p FROM Peticion p WHERE p.codigo = :codigo")
    List<Peticion> findByCodigo(@Param("codigo") String codigo); 
    
    @Query("SELECT SUM(p.horasReal) total FROM Imputacion p WHERE p.peticion.id = :id")
    Double sumHorasImputadas(@Param("id") Long id); 
    
    @Query(Constantes.QUERY_PETICION)
    List<Peticion> findByCriteria(
            @Param("idCategoria") Long idCategoria, 
            @Param("idSubCategoria") Long idSubCategoria,
            @Param("idEstado") Long estado,
            @Param("asuntoContiene") String asuntoContiene,
            @Param("inicioPrevistoDesde") String inicioPrevistoDesde,
            @Param("inicioPrevistoHasta") String inicioPrevistoHasta,
            @Param("inicioRealDesde") String inicioRealDesde,
            @Param("inicioRealHasta") String inicioRealHasta,
            @Param("finPrevistoDesde") String finPrevistoDesde,
            @Param("finPrevistoHasta") String finPrevistoHasta,
            @Param("finRealDesde") String finRealDesde,
            @Param("finRealHasta") String finRealHasta,
            @Param("horasPrevistaDesde") Double horasPrevistaDesde,
            @Param("horasPrevistaHasta") Double horasPrevistaHasta,
            @Param("horasRealDesde") Double horasRealDesde,
            @Param("horasRealHasta") Double horasRealHasta,
            @Param("porcentajeDesde") Double porcentajeDesde,
            @Param("porcentajeHasta") Double porcentajeHasta,
            //
            @Param("tipoListado") int tipoListado
    );              
}


