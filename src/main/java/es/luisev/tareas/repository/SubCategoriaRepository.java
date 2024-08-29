/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.repository;
import es.luisev.tareas.model.Categoria;
import es.luisev.tareas.model.Peticion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.luisev.tareas.model.SubCategoria;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Luis-Enrique.Varona
 */

@Repository
public interface SubCategoriaRepository extends JpaRepository<SubCategoria, Long> {
    
    @Query("SELECT p FROM Peticion p WHERE p.subCategoria.id = :id")
    List<Peticion> getPeticiones(@Param("id") Long id);      
    
    @Query("SELECT p FROM SubCategoria p WHERE p.categoria = :categoria")
    List<SubCategoria> findByCategoria(@Param("categoria") Categoria categoria);        
    
    @Query("SELECT p FROM SubCategoria p WHERE p.categoria.id = :idCategoria and p.codigo=:codigo")
    List<SubCategoria> findByCodigo(@Param("idCategoria") Long idCategoria, @Param("codigo") String codigo);        
}

