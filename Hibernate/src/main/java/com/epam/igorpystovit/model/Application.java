package com.epam.igorpystovit.model;

import com.epam.igorpystovit.model.dao.implementations.*;
import com.epam.igorpystovit.model.pojo.FlightsEntity;
import com.epam.igorpystovit.model.pojo.PlanesCompaniesEntity;
import com.epam.igorpystovit.model.pojo.sessionmanager.SessionManager;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.util.Optional;

public class Application {
    public static void main(String[] args) {
        Session session = SessionManager.getSession();
        try{
            PlanesCompaniesDAOImpl planesCompaniesDAO = new PlanesCompaniesDAOImpl();
            TownsDAOImpl townsDAO = new TownsDAOImpl();
            CompaniesDAOImpl companiesDAO = new CompaniesDAOImpl();
            FlightsDAOImpl flightsDAO = new FlightsDAOImpl();
//            flightsDAO.update(session,
//                    new FlightsEntity(3,2,2,1,
//                            "20120911","120000","20110909","200020",3,21000));
//            flightsDAO.create(session,new FlightsEntity(5,2,2,1,
//                    "2012-12-12","13:00:00","2012-12-12","20:00:00",3,2100));
            flightsDAO.getAll(session).forEach(System.out::println);
        } finally {
            session.close();
            SessionManager.shutdown();
        }

    }

}
