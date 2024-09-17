/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.service;

import es.luisev.tareas.exception.TareasApplicationException;
import es.luisev.tareas.model.Categoria;
import es.luisev.tareas.model.SubCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import es.luisev.tareas.repository.SubCategoriaRepository;
import es.luisev.tareas.utils.AppHelper;
import es.luisev.tareas.utils.Constantes;
import es.luisev.tareas.utils.UIHelper;


/**
 *
 * @author Luis-Enrique.Varona
 */
@Service(Constantes.SERVICIO_SUBCATEGORIA)
public class SubCategoriaService {

    @Autowired
    private SubCategoriaRepository repository;

    public List<SubCategoria> findAll() {
        return repository.findAll();
    }

    public List<SubCategoria> findByCategoria(Categoria categoria) {
        return repository.findByCategoria(categoria);
    }

    public SubCategoria insert(SubCategoria subCategoria) throws TareasApplicationException {
        validate(subCategoria);
        subCategoria.setFecAlta(AppHelper.getFechaAltaBd());
        return repository.save(subCategoria);
    }

    public Optional<SubCategoria> findById(Long id) {
        return repository.findById(id);
    }
    
    public SubCategoria findByCodigo(SubCategoria subCategoria) {
        if (subCategoria == null){
            return null;
        }
        List <SubCategoria> subCategorias = repository.findByCodigo(subCategoria.getCategoria().getId(), subCategoria.getCodigo());
        if (subCategorias != null && !subCategorias.isEmpty()){
            return subCategorias.get(0);
        }
        return null;
    }     

    public SubCategoria update(SubCategoria subCategoria) throws TareasApplicationException {
            validate(subCategoria);    
            return repository.save(subCategoria);
    }

    public void delete(Long id) throws TareasApplicationException {
        try{
        // Verificar si tiene dependencias en ese caso disparar error    
        repository.deleteById(id);
        } catch (Exception e){
            TareasApplicationException.raise(UIHelper.getLiteral("error.subcategoria.borrar", e.getMessage()) );
        }
    }
    
    private void validate(SubCategoria subCategoria) throws TareasApplicationException {
        if (subCategoria.getNombre().isEmpty()) {
            TareasApplicationException.raise(UIHelper.getLiteral("error.subcategoria.nombre.vacio"));
        }
        if (subCategoria.getCategoria() == null) {
            TareasApplicationException.raise(UIHelper.getLiteral("error.categoria.vacio"));
        }        
        SubCategoria subCategoriaDB = findByCodigo(subCategoria);
        if (subCategoriaDB != null && (subCategoria.getId() == null || !subCategoriaDB.getId().equals(subCategoria.getId()))){        
            TareasApplicationException.raise(UIHelper.getLiteral("error.subcategoria.codigo.duplicado"));
        }
    }    
}
