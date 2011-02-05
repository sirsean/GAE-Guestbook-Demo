package com.vikinghammer.dao;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

public abstract class BaseVHDao<T> implements VHDao<T> {
    protected final Logger log = Logger.getLogger(getClass().getName());

    @Autowired
    protected PersistenceManagerFactory pmFactory;

    @Override
    public void store(T model) {
        PersistenceManager pm = pmFactory.getPersistenceManager();
        try {
            pm.makePersistent(model);
        } finally {
            pm.close();
        }
    }

    protected List<T> list(String query) {
        log.info(String.format("list query: %s", query));
        PersistenceManager pm = pmFactory.getPersistenceManager();
        try {
            List<T> greetings = (List<T>)pm.newQuery(query).execute();
            greetings.size();
            return greetings;
        } finally {
            pm.close();
        }
    }

}
