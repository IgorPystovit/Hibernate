package com.epam.igorpystovit.services;

import com.epam.igorpystovit.model.dao.implementations.TownsDAOImpl;
import com.epam.igorpystovit.model.dao.interfaces.TownsDAO;
import com.epam.igorpystovit.model.pojo.TownsEntity;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class TownsService implements Service<TownsEntity,Integer>, TownsDAO {
    private static final TownsDAOImpl TOWNS_DAO = new TownsDAOImpl();

    @Override
    public List<TownsEntity> getAll(Session session) {
        return TOWNS_DAO.getAll(session);
    }

    @Override
    public Optional<TownsEntity> getById(Session session, Integer id) {
        return TOWNS_DAO.getById(session,id);
    }

    @Override
    public void create(Session session, TownsEntity entity) {
        TOWNS_DAO.create(session,entity);
    }

    @Override
    public void update(Session session, TownsEntity entity) {
        TOWNS_DAO.update(session,entity);
    }

    @Override
    public void delete(Session session, Integer id) {
        TOWNS_DAO.delete(session,id);
    }
}
