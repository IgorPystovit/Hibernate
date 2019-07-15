package com.epam.igorpystovit.dao;

import com.epam.igorpystovit.model.OrdersEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl {
    public List<OrdersEntity> getAll(Session session){
        List<OrdersEntity> orders = new ArrayList<>();

        Query query = session.createQuery("from OrdersEntity ");
        for (Object order : query.list()){
            orders.add((OrdersEntity)order);
        }
        return orders;
    }
}
