package com.epam.igorpystovit.model.dao.interfaces;

import com.epam.igorpystovit.model.pojo.OrdersEntity;
import org.hibernate.Session;

public interface OrdersDAO extends GeneralDAO<OrdersEntity,Integer>{
    void updateFlightId(Session session,Integer updateId,Integer newFlightId);
    void updateClientId(Session session,Integer updateId,Integer newClientId);
}
