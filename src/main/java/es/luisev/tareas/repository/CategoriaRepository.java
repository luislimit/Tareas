/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.luisev.tareas.model.Categoria;
import es.luisev.tareas.model.Peticion;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Luis-Enrique.Varona
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("SELECT p FROM Peticion p WHERE p.categoria.id = :id")
    List<Peticion> getPeticiones(@Param("id") Long id);

    @Query("SELECT p FROM SubCategoria p WHERE p.categoria.id = :id")
    List<Peticion> getSubCategorias(@Param("id") Long id);

    @Query("SELECT p FROM Categoria p WHERE p.codigo = :codigo")
    List<Categoria> findByCodigo(@Param("codigo") String codigo);

}
