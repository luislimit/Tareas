/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.service;

import es.luisev.tareas.exception.TareasApplicationException;
import es.luisev.tareas.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import es.luisev.tareas.repository.CategoriaRepository;
import es.luisev.tareas.utils.Constantes;
import es.luisev.tareas.utils.UIHelper;
import java.util.Date;

/**
 *
 * @author Luis-Enrique.Varona
 */
@Service(Constantes.SERVICIO_CATEGORIA)
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    public Categoria insert(Categoria categoria) throws TareasApplicationException {
        validate(categoria);
        Date ahora = new Date();
        categoria.setFecAlta(ahora.getTime());
        return repository.save(categoria);
    }

    public Optional<Categoria> findById(Long id) {
        return repository.findById(id);
    }

    public Categoria update(Categoria categoria) throws TareasApplicationException {
        validate(categoria);
        return repository.save(categoria);
    }

    public void delete(Long id) throws TareasApplicationException {
        if (!repository.getSubCategorias(id).isEmpty()) {
            TareasApplicationException.raise(UIHelper.getLiteral("error.categoria.con.subcategorias"));
        }
        if (!repository.getPeticiones(id).isEmpty()) {
            TareasApplicationException.raise(UIHelper.getLiteral("error.categoria.con.peticiones"));
        }
        repository.deleteById(id);
    }

   
    public Categoria findByCodigo(Categoria categoria) {
        if (categoria == null){
            return null;
        }
        List <Categoria> categorias = repository.findByCodigo(categoria.getCodigo());
        if (categorias != null && !categorias.isEmpty()){
            return categorias.get(0);
        }
        return null;
    }    

    private void validate(Categoria categoria) throws TareasApplicationException {
        if (categoria.getCodigo().isEmpty()) {
            TareasApplicationException.raise(UIHelper.getLiteral("error.categoria.codigo.vacio"));
        }
        if (categoria.getNombre().isEmpty()) {
            TareasApplicationException.raise(UIHelper.getLiteral("error.categoria.nombre.vacio"));
        }        
        Categoria categoriaDB = findByCodigo(categoria);
        if (categoriaDB != null && (categoria.getId() == null || !categoriaDB.getId().equals(categoria.getId()))){
            TareasApplicationException.raise(UIHelper.getLiteral("error.categoria.codigo.duplicado"));
        }
    }
}
