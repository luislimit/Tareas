/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.luisev.tareas.model.Usuario;

/**
 *
 * @author Luis-Enrique.Varona
 */

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
