package br.com.diisk.aluraloja.testes;

import java.math.BigDecimal;

import br.com.diisk.aluraloja.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CadastroDeProduto {

    public static void main(String[] args) {
        Produto celular = new Produto();
        celular.setNome("Teste Nome");
        celular.setDescricao("Teste Desc");
        celular.setPreco(new BigDecimal("500"));

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("alura-loja");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(celular);
        em.getTransaction().commit();
        em.close();
    }

}
