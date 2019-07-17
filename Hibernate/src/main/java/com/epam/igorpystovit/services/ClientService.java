package com.epam.igorpystovit.services;

import com.epam.igorpystovit.model.dao.implementations.ClientsDAOImpl;
import com.epam.igorpystovit.model.dao.interfaces.ClientsDAO;
import com.epam.igorpystovit.model.pojo.ClientsEntity;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class ClientService implements Service<ClientsEntity,Integer>, ClientsDAO {
    private static final ClientsDAOImpl CLIENTS_DAO = new ClientsDAOImpl();

    @Override
    public List<ClientsEntity> getAll(Session session) {
        return CLIENTS_DAO.getAll(session);
    }

    @Override
    public Optional<ClientsEntity> getById(Session session, Integer id) {
        return CLIENTS_DAO.getById(session,id);
    }

    @Override
    public void create(Session session, ClientsEntity entity) {
        CLIENTS_DAO.create(session,entity);
    }

    @Override
    public void update(Session session, ClientsEntity entity) {
        CLIENTS_DAO.update(session,entity);
    }

    @Override
    public void delete(Session session, Integer id) {
        CLIENTS_DAO.delete(session,id);
    }
}
