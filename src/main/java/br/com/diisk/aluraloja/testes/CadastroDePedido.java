package br.com.diisk.aluraloja.testes;

import java.math.BigDecimal;

import br.com.diisk.aluraloja.dao.CategoriaDao;
import br.com.diisk.aluraloja.dao.ClienteDao;
import br.com.diisk.aluraloja.dao.PedidoDao;
import br.com.diisk.aluraloja.dao.ProdutoDao;
import br.com.diisk.aluraloja.model.Categoria;
import br.com.diisk.aluraloja.model.Cliente;
import br.com.diisk.aluraloja.model.ItemPedido;
import br.com.diisk.aluraloja.model.Pedido;
import br.com.diisk.aluraloja.model.Produto;
import br.com.diisk.aluraloja.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class CadastroDePedido {

    public static void main(String[] args) {
        popularBanco();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        var produto = produtoDao.buscarPorId(1l);
        var clienteDao = new ClienteDao(em);
        var cliente = clienteDao.buscarPorId(1l);
        em.getTransaction().begin();

        var pedido = new Pedido(cliente);
        pedido.addItem(new ItemPedido(10, produto));
        PedidoDao pedidoDao = new PedidoDao(em);
        pedidoDao.cadastrar(pedido);
        em.getTransaction().commit();

        BigDecimal totalVendido = pedidoDao.getValorTotalVendido();
        System.out.println(totalVendido);

        var relatorio = pedidoDao.getRelatorioVendas();
        relatorio.forEach(System.out::println);
    }

    private static void popularBanco() {
        Categoria celulares = new Categoria("Celulares");
        Produto celular = new Produto("Teste Nome", "Teste Desc", new BigDecimal("500"), celulares);
        Cliente cliente = new Cliente("Teste", "1241524");

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        var clienteDao = new ClienteDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();
        clienteDao.cadastrar(cliente);
        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);
        em.getTransaction().commit();
        em.close();
    }

}
