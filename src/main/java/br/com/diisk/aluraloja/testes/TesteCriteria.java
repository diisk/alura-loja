package br.com.diisk.aluraloja.testes;

import java.math.BigDecimal;
import java.util.List;

import br.com.diisk.aluraloja.dao.CategoriaDao;
import br.com.diisk.aluraloja.dao.ProdutoDao;
import br.com.diisk.aluraloja.model.Categoria;
import br.com.diisk.aluraloja.model.Produto;
import br.com.diisk.aluraloja.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class TesteCriteria {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        List<Produto> buscarPorParametros = produtoDao.buscarPorParametros(null, null, null);
        buscarPorParametros.forEach(System.out::println);
    }

}
