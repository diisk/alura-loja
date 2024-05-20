package br.com.diisk.aluraloja.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.diisk.aluraloja.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ProdutoDao {

    private EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto) {
        this.em.persist(produto);
    }

    public Produto buscarPorId(Long id) {
        return em.find(Produto.class, id);
    }

    public List<Produto> buscarPorParametros(
            String nome,
            BigDecimal preco,
            LocalDate dataCadastro) {
        String jpql = """
                SELECT p FROM Produto p
                WHERE
                """;
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Produto> query = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> from = query.from(Produto.class);

        Predicate filters = criteriaBuilder.and();
        if (nome != null && !nome.trim().isEmpty()) {
            filters = criteriaBuilder.and(filters, criteriaBuilder.equal(from.get("nome"), nome));
        }

        if (preco != null) {
            filters = criteriaBuilder.and(filters, criteriaBuilder.equal(from.get("preco"), preco));
        }

        if (dataCadastro != null) {
            filters = criteriaBuilder.and(filters, criteriaBuilder.equal(from.get("dataCadastro"), dataCadastro));
        }

        query.where(filters);

        return em.createQuery(query).getResultList();
    }

    public List<Produto> buscarPorNomeCategoria(String nome) {
        nome = "%" + nome + "%";
        String jpql = "SELECT p FROM Produto p WHERE p.categoria.descricao like :nome";
        return em.createNamedQuery("Produto.produtosPorCategoria", Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public BigDecimal buscarPrecoProduto(String nome) {
        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
        return em.createQuery(jpql, BigDecimal.class)
                .setParameter("nome", nome)
                .getSingleResult();
    }

    public List<Produto> buscarPorNome(String nome) {
        nome = "%" + nome + "%";
        String jpql = "SELECT p FROM Produto p WHERE p.nome like :nome";
        return em.createQuery(jpql, Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public List<Produto> buscarTodos() {
        String jpql = "SELECT p FROM Produto p";
        return em.createQuery(jpql, Produto.class).getResultList();
    }

}
