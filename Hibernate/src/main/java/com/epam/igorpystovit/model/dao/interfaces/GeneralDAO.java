package com.epam.igorpystovit.model.dao.interfaces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface GeneralDAO<T,ID> {
    Logger logger = LogManager.getLogger(GeneralDAO.class);
    List<T> getAll(Session session);
    Optional<T> getById(Session session, ID id);
    void create(Session session,T entity);
    void update(Session session,T entity);
    void delete(Session session,ID id);
    default boolean isPresent(Session session,ID id){
        return getById(session,id).isPresent();
    }
}
