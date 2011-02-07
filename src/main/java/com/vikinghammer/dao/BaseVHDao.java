package com.vikinghammer.dao;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

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

    protected List<T> list(VHQuery vhQuery, Object... args) {
        PersistenceManager pm = pmFactory.getPersistenceManager();
        try {
            Query query = pm.newQuery(vhQuery.getClazz());
            if (vhQuery.hasFilter()) {
                query.setFilter(vhQuery.getFilter());
            }
            if (vhQuery.hasOrdering()) {
                query.setOrdering(vhQuery.getOrdering());
            }
            if (vhQuery.hasRange()) {
                query.setRange(vhQuery.getRangeStart(), vhQuery.getRangeEnd());
            }
            List<T> list = (List<T>)query.executeWithArray(args);
            list.size();
            return list;
        } finally {
            pm.close();
        }
    }

    protected T first(VHQuery vhQuery, Object... args) {
        PersistenceManager pm = pmFactory.getPersistenceManager();
        try {
            Query query = pm.newQuery(vhQuery.getClazz());
            if (vhQuery.hasFilter()) {
                query.setFilter(vhQuery.getFilter());
            }
            if (vhQuery.hasOrdering()) {
                query.setOrdering(vhQuery.getOrdering());
            }
            query.setRange(0, 1);
            List<T> list = (List<T>)query.executeWithArray(args);
            if (list.size() > 0) {
                return list.get(0);
            } else {
                return null;
            }
        } finally {
            pm.close();
        }
    }

}
