/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.luisev.tareas.model;

import java.util.Objects;

/**
 *
 * @author Luis-Enrique.Varona
 */
public abstract class DataBaseEntity {
    public Long getPrimaryKey(){
        return null;
    }
    
   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataBaseEntity dBEntity = (DataBaseEntity) o;
        return this.getPrimaryKey().equals(dBEntity.getPrimaryKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrimaryKey());
    }    
    
}
