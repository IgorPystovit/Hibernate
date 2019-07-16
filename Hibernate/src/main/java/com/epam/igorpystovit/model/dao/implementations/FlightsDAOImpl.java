package com.epam.igorpystovit.model.dao.implementations;

import com.epam.igorpystovit.model.dao.interfaces.FlightsDAO;
import com.epam.igorpystovit.model.pojo.FlightsEntity;
import com.epam.igorpystovit.model.pojo.PlanesCompaniesEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unchecked")
public class FlightsDAOImpl implements FlightsDAO {

    @Override
    public List<FlightsEntity> getAll(Session session) {
        Query<FlightsEntity> query = session.createQuery("from FlightsEntity ");
        session.beginTransaction();
        List<FlightsEntity> flights = query.list();
        session.getTransaction().commit();
        return flights;
    }

    @Override
    public Optional<FlightsEntity> getById(Session session, Integer id) {
        Query<FlightsEntity> query = session.createQuery("from FlightsEntity where id = : code");
        query.setParameter("code",id);
        Optional<FlightsEntity> flight = Optional.empty();
        if (!query.list().isEmpty()){
            flight = Optional.ofNullable(query.list().get(0));
        }
        return flight;
    }


    @Override
    public void create(Session session, FlightsEntity entity) {
        PlanesCompaniesDAOImpl planesCompaniesDAO = new PlanesCompaniesDAOImpl();
        TownsDAOImpl townsDAO = new TownsDAOImpl();
        CompaniesDAOImpl companiesDAO = new CompaniesDAOImpl();
        if (!isPresent(session,entity.getId())){
            session.beginTransaction();
            entity.setCompaniesByCompanyId(companiesDAO.getById(session,entity.getCompanyId()).get());
            entity.setPlanesCompaniesByPlaneId(planesCompaniesDAO.getById(session,entity.getPlaneId()).get());
            entity.setTownsByArrivalTownId(townsDAO.getById(session,entity.getArrivalTownId()).get());
            entity.setTownsByDepartureTownId(townsDAO.getById(session,entity.getDepartureTownId()).get());
            session.save(entity);
            session.getTransaction().commit();
        }
        else{
            logger.info("You are trying to insert duplicate primary key");
        }
    }

    @Override
    public void update(Session session, FlightsEntity entity) {
        PlanesCompaniesDAOImpl planesCompaniesDAO = new PlanesCompaniesDAOImpl();
        TownsDAOImpl townsDAO = new TownsDAOImpl();
        CompaniesDAOImpl companiesDAO = new CompaniesDAOImpl();
        if (isPresent(session,entity.getId())){
            FlightsEntity flight = getById(session,entity.getId()).get();
            session.beginTransaction();
            flight.setCompanyId(entity.getCompanyId());
            flight.setDepartureTownId(entity.getDepartureTownId());
            flight.setArrivalTownId(entity.getArrivalTownId());
            flight.setDepartureDate(entity.getDepartureDate());
            flight.setDepartureTime(entity.getDepartureTime());
            flight.setArrivalDate(entity.getArrivalDate());
            flight.setArrivalTime(entity.getArrivalTime());
            flight.setPlaneId(entity.getPlaneId());
            flight.setPrice(entity.getPrice());
            flight.setCompaniesByCompanyId(companiesDAO.getById(session,entity.getCompanyId()).get());
            flight.setPlanesCompaniesByPlaneId(planesCompaniesDAO.getById(session,entity.getPlaneId()).get());
            flight.setTownsByArrivalTownId(townsDAO.getById(session,entity.getArrivalTownId()).get());
            flight.setTownsByDepartureTownId(townsDAO.getById(session,entity.getDepartureTownId()).get());
            session.getTransaction().commit();
        }
        else {
            logger.info("A row you are trying to update");
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
