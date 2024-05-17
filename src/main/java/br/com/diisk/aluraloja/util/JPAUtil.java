package br.com.diisk.aluraloja.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("alura-loja");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }

}
