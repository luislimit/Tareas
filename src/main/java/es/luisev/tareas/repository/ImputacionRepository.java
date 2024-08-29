/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.luisev.tareas.model.Imputacion;
import es.luisev.tareas.model.Peticion;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Luis-Enrique.Varona
 */

@Repository
public interface ImputacionRepository extends JpaRepository<Imputacion, Long> {
    
    @Query("SELECT p FROM Imputacion p WHERE p.peticion IN :peticiones")
    List<Imputacion> findByPeticiones(@Param("peticiones") List<Peticion> peticion);        
}

