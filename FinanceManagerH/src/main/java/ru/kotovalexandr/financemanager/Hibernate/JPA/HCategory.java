package ru.kotovalexandr.financemanager.Hibernate.JPA;

import ru.kotovalexandr.financemanager.HModel.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexkotov on 26.09.16.
 */
public class HCategory implements IDBHelper<Category> {
    private EntityManagerFactory entityManagerFactory;
    private static HCategory instance = new HCategory();
    private HCategory(){
        entityManagerFactory = Persistence.createEntityManagerFactory("ru.kotovalexandr.financemanager.HModel");
    }
    public static HCategory getInstance(){return instance;}


    @Override
    public void save(Category category) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(category);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void update(Category category) {

    }

    @Override
    public void delete(Category category) {

    }

    @Override
    public Category getByID(int id) {
        return null;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        categories = entityManager.createQuery("from Category", Category.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return categories;
    }
}
