package br.com.diisk.aluraloja.dao;

import java.math.BigDecimal;
import java.util.List;

import br.com.diisk.aluraloja.model.Pedido;
import br.com.diisk.aluraloja.vo.RelatorioVendasVo;
import jakarta.persistence.EntityManager;

public class PedidoDao {

    private EntityManager em;

    public PedidoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Pedido pedido) {
        this.em.persist(pedido);
    }

    public Pedido buscaPedidoComCliente(Long id) {
        return em.createQuery("""
                SELECT p FROM Pedido p
                JOIN FETCH p.cliente
                WHERE p.id = :id
                """, Pedido.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public BigDecimal getValorTotalVendido() {
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return em.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

    public List<RelatorioVendasVo> getRelatorioVendas() {
        String jpql = """
                       SELECT new RelatorioVendasVo(
                       produto.nome,
                       SUM(item.quantidade) AS totalQuantidade,
                       MAX(pedido.data) AS ultimaData)
                FROM Pedido pedido
                JOIN pedido.itens item
                JOIN item.produto produto
                GROUP BY produto.nome
                ORDER BY totalQuantidade DESC
                               """;
        return em.createQuery(jpql, RelatorioVendasVo.class).getResultList();
    }

}
