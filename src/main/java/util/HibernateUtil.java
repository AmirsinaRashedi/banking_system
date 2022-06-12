package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

    private static final EntityManagerFactory emf;

    private static EntityManager entityManager;

    static {
        emf = Persistence.createEntityManagerFactory("banking_system");
    }

    private HibernateUtil() {
    }

    public static EntityManagerFactory getEmf() {
        return emf;
    }

    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            entityManager = emf.createEntityManager();
        }

        return entityManager;
    }

    public static void closeEntityManager() {
        if (entityManager != null)
            entityManager.close();
    }
}
