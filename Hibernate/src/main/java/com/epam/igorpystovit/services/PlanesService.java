package com.epam.igorpystovit.services;

import com.epam.igorpystovit.model.dao.implementations.PlanesDAOImpl;
import com.epam.igorpystovit.model.dao.interfaces.PlanesDAO;
import com.epam.igorpystovit.model.pojo.PlanesEntity;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class PlanesService implements Service<PlanesEntity,Integer> , PlanesDAO {
    private static final PlanesDAOImpl PLANES_DAO = new PlanesDAOImpl();

    @Override
    public List<PlanesEntity> getAll(Session session) {
        return PLANES_DAO.getAll(session);
    }

    @Override
    public Optional<PlanesEntity> getById(Session session, Integer id) {
        return PLANES_DAO.getById(session,id);
    }

    @Override
    public void create(Session session, PlanesEntity entity) {
        PLANES_DAO.create(session,entity);
    }

    @Override
    public void update(Session session, PlanesEntity entity) {
        PLANES_DAO.update(session,entity);
    }

    @Override
    public void delete(Session session, Integer id) {
        PLANES_DAO.delete(session,id);
    }
}
