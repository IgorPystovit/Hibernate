package com.epam.igorpystovit.model.dao.implementations;

import com.epam.igorpystovit.model.dao.interfaces.ClientsDAO;
import com.epam.igorpystovit.model.pojo.ClientsEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unchecked")
public class ClientsDAOImpl implements ClientsDAO {
    @Override
    public List<ClientsEntity> getAll(Session session) {
        Query<ClientsEntity> query = session.createQuery("from ClientsEntity ");
        session.beginTransaction();
        List<ClientsEntity> clients = query.list();
        session.getTransaction().commit();
        return clients;
    }

    @Override
    public Optional<ClientsEntity> getById(Session session, Integer id) {
        Query<ClientsEntity> query = session.createQuery("from ClientsEntity where id = : code");
        query.setParameter("code",id);
        Optional<ClientsEntity> client = Optional.empty();
        if (!query.list().isEmpty()){
            client = Optional.ofNullable(query.list().get(0));
        }
        else{
            logger.info("There is no such row");
        }
        return client;
    }

    @Override
    public void create(Session session, ClientsEntity entity) {
        if (!isPresent(session,entity.getId())){
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        }
        else{
            logger.info("You are trying to insert duplicate key");
        }
    }

    @Override
    public void update(Session session, ClientsEntity entity) {
        if (isPresent(session,entity.getId())){
            ClientsEntity client = getById(session,entity.getId()).get();
            session.beginTransaction();
            client.setName(entity.getName());
            client.setSurname(entity.getSurname());
            client.setCash(entity.getCash());
            session.getTransaction().commit();
        }
        else{
            logger.info("A row you are trying to update does not exist");
        }
    }

    @Override
    public void delete(Session session, Integer id) {
        if (isPresent(session,id)){
            session.beginTransaction();
            session.remove(getById(session,id).get());
            session.getTransaction().commit();
        }
        else{
            logger.info("A row you are trying to delete does not exist");
        }
    }
}
