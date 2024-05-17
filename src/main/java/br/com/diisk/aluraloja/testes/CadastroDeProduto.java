package br.com.diisk.aluraloja.testes;

import java.math.BigDecimal;

import br.com.diisk.aluraloja.dao.CategoriaDao;
import br.com.diisk.aluraloja.dao.ProdutoDao;
import br.com.diisk.aluraloja.model.Categoria;
import br.com.diisk.aluraloja.model.Produto;
import br.com.diisk.aluraloja.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class CadastroDeProduto {

    public static void main(String[] args) {
        Categoria celulares = new Categoria("Celulares");
        Produto celular = new Produto("Teste Nome", "Teste Desc", new BigDecimal("500"), celulares);

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();
        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);
        em.getTransaction().commit();
        em.close();
    }

}
