package br.com.diisk.aluraloja.dao;

import br.com.diisk.aluraloja.model.Cliente;
import br.com.diisk.aluraloja.model.Produto;
import jakarta.persistence.EntityManager;

public class ClienteDao {

    private EntityManager em;

    public ClienteDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Cliente cliente) {
        this.em.persist(cliente);
    }

    public Cliente buscarPorId(long id) {
        return em.find(Cliente.class, id);
    }

}
