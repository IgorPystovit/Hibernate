package com.epam.igorpystovit.model;

import com.epam.igorpystovit.model.dao.implementations.*;
import com.epam.igorpystovit.model.pojo.ClientsEntity;
import com.epam.igorpystovit.model.pojo.FlightsEntity;
import com.epam.igorpystovit.model.pojo.OrdersEntity;
import com.epam.igorpystovit.model.pojo.PlanesCompaniesEntity;
import com.epam.igorpystovit.model.pojo.sessionmanager.SessionManager;
import com.epam.igorpystovit.services.*;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.util.Optional;

public class Application {
    public static void main(String[] args) {
        Session session = SessionManager.getSession();
        try{
            PlanesCompaniesService planesCompaniesService = new PlanesCompaniesService();
            TownsService townsService = new TownsService();
            FlightsService flightsService = new FlightsService();
            ClientService clientService = new ClientService();
            OrdersService ordersService = new OrdersService();
//            flightsService.create(session,new FlightsEntity(2,1,3,
//                    "20110101","210000","20110102","093000",9,2000));
//            clientService.update(session,new ClientsEntity(1,"Some","Client",20000));
//            ordersService.update(session,new OrdersEntity(1,1,1));
//            ordersService.delete(session,1);
            flightsService.update(session,new FlightsEntity(2,1,3,
                    "20110101","210000","20110102","093000",1,200));
            flightsService.getAll(session).forEach(System.out::println);
        } finally {
            session.close();
            SessionManager.shutdown();
        }

    }

}
