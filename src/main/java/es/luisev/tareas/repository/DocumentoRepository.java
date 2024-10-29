/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.luisev.tareas.model.Documento;
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
public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    
    @Query(Constantes.QUERY_DOCUMENTO)
    List<Documento> findByCriteria(
            @Param("peticiones") List<Peticion> peticion,
            @Param("documentoContiene") String documentoContiene,
            @Param("ficheroContiene") String ficheroContiene,
            @Param("idTipoDocumento") Long idTipoDocumento
    );         
}

