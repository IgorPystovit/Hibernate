package com.epam.igorpystovit.model.services;

import com.epam.igorpystovit.model.dao.implementations.ClientsDAOImpl;
import com.epam.igorpystovit.model.pojo.ClientsEntity;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class ClientsService implements Service<ClientsEntity,Integer>{
    private ClientsDAOImpl clientsDAO = new ClientsDAOImpl();

    @Override
    public List<ClientsEntity> getAll(Session session) {
        return clientsDAO.getAll(session);
    }

    @Override
    public Optional<ClientsEntity> getById(Session session, Integer id) {
        return clientsDAO.getById(session,id);
    }

    @Override
    public void create(Session session, ClientsEntity entity) {
        clientsDAO.create(session,entity);
    }

    @Override
    public void update(Session session, ClientsEntity entity) {
        clientsDAO.update(session,entity);
    }

    @Override
    public void delete(Session session, Integer id) {
        clientsDAO.delete(session,id);
    }
}
