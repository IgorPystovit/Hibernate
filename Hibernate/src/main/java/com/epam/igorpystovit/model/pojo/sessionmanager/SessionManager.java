package com.epam.igorpystovit.model.pojo.sessionmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionManager {

    private static SessionFactory sessionFactory;

    public static Session getSession(){
        if (sessionFactory == null) {
            try{
                sessionFactory = new Configuration().configure().buildSessionFactory();
            } catch (Throwable e){
                System.out.println(e);
                System.out.println("Cannot create session");
            }
        }
        return sessionFactory.openSession();
    }

    public static void shutdown(){
        if (sessionFactory != null){
            sessionFactory.close();
        }
    }
}
