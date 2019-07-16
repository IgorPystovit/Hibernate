package com.epam.igorpystovit.model.dao.implementations;

import com.epam.igorpystovit.model.dao.interfaces.PlanesDAO;
import com.epam.igorpystovit.model.pojo.PlanesEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unchecked")
public class PlanesDAOImpl implements PlanesDAO {
    @Override
    public List<PlanesEntity> getAll(Session session) {
        Query<PlanesEntity> query = session.createQuery("from PlanesEntity ");
        session.beginTransaction();
        List<PlanesEntity> planes = query.list();
        session.getTransaction().commit();
        return planes;
    }

    @Override
    public Optional<PlanesEntity> getById(Session session, Integer id) {
        Query<PlanesEntity> query = session.createQuery("from  PlanesEntity where id = : code");
        query.setParameter("code",id);
        Optional<PlanesEntity> plane = Optional.empty();
        if (!query.list().isEmpty()){
            plane = Optional.ofNullable(query.list().get(0));
        }
        return plane;
    }

    @Override
    public void create(Session session, PlanesEntity entity) {
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
    public void update(Session session, PlanesEntity entity) {
        if (isPresent(session,entity.getId())){
            PlanesEntity plane = getById(session,entity.getId()).get();
            session.beginTransaction();
            plane.setPlaneName(entity.getPlaneName());
            plane.setCapacity(entity.getCapacity());
            plane.setPlaneType(entity.getPlaneType());
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
