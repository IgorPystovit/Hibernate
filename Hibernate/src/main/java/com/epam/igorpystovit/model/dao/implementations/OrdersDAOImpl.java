package com.epam.igorpystovit.model.dao.implementations;

import com.epam.igorpystovit.model.dao.interfaces.ClientsDAO;
import com.epam.igorpystovit.model.dao.interfaces.OrdersDAO;
import com.epam.igorpystovit.model.pojo.OrdersEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unchecked")
public class OrdersDAOImpl implements OrdersDAO {
    public List<OrdersEntity> getAll(Session session){
        Query query = session.createQuery("from OrdersEntity ");
        session.beginTransaction();
        List<OrdersEntity> orders = query.list();
        session.getTransaction().commit();
        return orders;
    }

    @Override
    public Optional<OrdersEntity> getById(Session session, Integer id) {
        Query<OrdersEntity> query = session.createQuery("from OrdersEntity where id = : code");
        query.setParameter("code",id);
        Optional<OrdersEntity> order = Optional.empty();
        if (!query.list().isEmpty()){
            order = Optional.ofNullable(query.list().get(0));
        }
        return order;
    }

    @Override
    public void create(Session session, OrdersEntity entity) {
        FlightsDAOImpl flightsDAO = new FlightsDAOImpl();
        ClientsDAOImpl clientsDAO = new ClientsDAOImpl();
        if (!isPresent(session,entity.getId())){
            session.beginTransaction();
            entity.setClientsByClientId(clientsDAO.getById(session,entity.getClientId()).get());
            entity.setFlightsByFlightId(flightsDAO.getById(session,entity.getFlightId()).get());
            session.save(entity);
            session.getTransaction().commit();
        }
        else{
            logger.info("You are trying to insert duplicate primary key");
        }
    }

    @Override
    public void update(Session session, OrdersEntity entity) {
        ClientsDAOImpl clientsDAO = new ClientsDAOImpl();
        FlightsDAOImpl flightsDAO = new FlightsDAOImpl();
        if (isPresent(session,entity.getId())){
            OrdersEntity order = getById(session,entity.getId()).get();
            session.beginTransaction();
            order.setClientId(entity.getClientId());
            order.setFlightId(entity.getFlightId());
            order.setClientsByClientId(clientsDAO.getById(session,entity.getClientId()).get());
            order.setFlightsByFlightId(flightsDAO.getById(session,entity.getFlightId()).get());
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
