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
        cadastrarProduto();
        Long id = 1l;

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        Produto produto = produtoDao.buscarPorId(id);
        var todos = produtoDao.buscarPorNomeCategoria("Celulares");
        todos.forEach(System.out::println);
        var prod = produtoDao.buscarPrecoProduto("Teste Nome");
        System.out.println("tst");
        System.out.println(prod);
    }

    private static void cadastrarProduto() {
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
