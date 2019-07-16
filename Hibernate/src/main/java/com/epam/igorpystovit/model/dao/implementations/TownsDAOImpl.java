package com.epam.igorpystovit.model.dao.implementations;

import com.epam.igorpystovit.model.dao.interfaces.TownsDAO;
import com.epam.igorpystovit.model.pojo.PlanesEntity;
import com.epam.igorpystovit.model.pojo.TownsEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unchecked")
public class TownsDAOImpl implements TownsDAO {
    @Override
    public List<TownsEntity> getAll(Session session) {
        Query<TownsEntity> query = session.createQuery("from TownsEntity ");
        session.beginTransaction();
        List<TownsEntity> towns = query.list();
        session.getTransaction().commit();
        return towns;
    }

    @Override
    public Optional<TownsEntity> getById(Session session, Integer id) {
        Query<TownsEntity> query = session.createQuery("from TownsEntity where id = : code");
        query.setParameter("code",id);
        Optional<TownsEntity> town = Optional.empty();
        if (!query.list().isEmpty()){
            town = Optional.ofNullable(query.list().get(0));
        }
        return town;
    }

    @Override
    public void create(Session session, TownsEntity entity) {
        if (!isPresent(session,entity.getId())){
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        }
        else{
            logger.info("You are trying to insert duplicate primary key");
        }
    }

    @Override
    public void update(Session session, TownsEntity entity) {
        if (isPresent(session,entity.getId())){
            TownsEntity town = getById(session,entity.getId()).get();
            session.beginTransaction();
            town.setName(entity.getName());
            session.getTransaction().commit();
        }
        else {
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
        else {
            logger.info("A row you are trying to delete does not exist");
        }
    }
}
