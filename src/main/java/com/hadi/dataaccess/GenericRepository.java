package com.hadi.dataaccess;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * If you want to access to entityManager in your RepositoryImpl of your Entities, you have to extends this class.
 *
 * @author h.mohammadi
 */
public abstract class GenericRepository {

    @PersistenceContext
    private EntityManager entityManager;

    protected final EntityManager getEntityManager() {
        return entityManager;
    }
}