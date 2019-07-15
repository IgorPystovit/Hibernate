package com.epam.igorpystovit.model;

import com.epam.igorpystovit.dao.ClientsDAOImpl;
import com.epam.igorpystovit.dao.OrderDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Application {
    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static Session getSession(){
        return sessionFactory.openSession();
    }

    public static void main(String[] args) {
        ClientsDAOImpl clientsDAO = new ClientsDAOImpl();
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        Session session = getSession();

        try{
            clientsDAO.createClient(session,new ClientsEntity(4,"Sth","Smb",new BigDecimal(3000)));
            clientsDAO.getAllClients(session).forEach(System.out::println);
        } finally {
            session.close();
        }

    }

}
