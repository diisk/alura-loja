package br.com.diisk.aluraloja.dao;

import br.com.diisk.aluraloja.model.Categoria;
import jakarta.persistence.EntityManager;

public class CategoriaDao {

    private EntityManager em;

    public CategoriaDao(EntityManager em){
        this.em = em;
    }

    public void cadastrar(Categoria categoria
    ){
        this.em.persist(categoria);
    }

}
