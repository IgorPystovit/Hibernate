package com.epam.igorpystovit.model.dao.implementations;

import com.epam.igorpystovit.model.dao.interfaces.FlightsDAO;
import com.epam.igorpystovit.model.pojo.DateTimeParser;
import com.epam.igorpystovit.model.pojo.FlightsEntity;
import com.epam.igorpystovit.model.pojo.PlanesCompaniesEntity;
import com.epam.igorpystovit.model.pojo.TownsEntity;
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
        if (!isPresent(session,entity.getId())){
            PlanesCompaniesEntity planeCompany = planesCompaniesDAO.getById(session,entity.getPlaneCompanyId()).get();
            TownsEntity departureTown = townsDAO.getById(session,entity.getDepartureTownId()).get();
            TownsEntity arrivalTown = townsDAO.getById(session,entity.getArrivalTownId()).get();
            session.beginTransaction();
            entity.setPlanesCompaniesByPlaneId(planeCompany);
            entity.setTownsByArrivalTownId(arrivalTown);
            entity.setTownsByDepartureTownId(departureTown);
            planeCompany.getFlightsById().add(entity);
            departureTown.getFlightsById_departure().add(entity);
            arrivalTown.getFlightsById_arrival().add(entity);
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
        if (isPresent(session,entity.getId())){
            FlightsEntity flight = getById(session,entity.getId()).get();
            session.beginTransaction();
            flight.setDepartureTownId(entity.getDepartureTownId());
            flight.setArrivalTownId(entity.getArrivalTownId());
            flight.setDepartureDate(entity.getDepartureDate());
            flight.setDepartureTime(entity.getDepartureTime());
            flight.setArrivalDate(entity.getArrivalDate());
            flight.setArrivalTime(entity.getArrivalTime());
            flight.setPlaneCompanyId(entity.getPlaneCompanyId());
            flight.setPrice(entity.getPrice());
            flight.setPlanesCompaniesByPlaneId(planesCompaniesDAO.getById(session,entity.getPlaneCompanyId()).get());
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
        PlanesCompaniesDAOImpl planesCompaniesDAO = new PlanesCompaniesDAOImpl();
        TownsDAOImpl townsDAO = new TownsDAOImpl();
        if (isPresent(session,id)){
            FlightsEntity flight = getById(session,id).get();
            PlanesCompaniesEntity planeCompany = planesCompaniesDAO.getById(session,flight.getPlaneCompanyId()).get();
            TownsEntity departureTown = townsDAO.getById(session,flight.getDepartureTownId()).get();
            TownsEntity arrivalTown = townsDAO.getById(session,flight.getArrivalTownId()).get();
            session.beginTransaction();
            planeCompany.getFlightsById().remove(flight);
            departureTown.getFlightsById_departure().remove(flight);
            arrivalTown.getFlightsById_arrival().remove(flight);
            session.remove(flight);
            session.getTransaction().commit();
        }
        else {
            logger.info("A row you are trying to delete does not exist");
        }
    }

    @Override
    public void updateDateTime(Session session, Integer updateFlightId, String newDate, String newTime, FlightDateTimeType dateTimeType) {
        if (isPresent(session,updateFlightId)){
            FlightsEntity flight = getById(session,updateFlightId).get();
            session.beginTransaction();
            switch (dateTimeType){
                case ARRIVAL:
                    flight.setArrivalDate(newDate);
                    flight.setArrivalTime(newTime);
                    session.getTransaction().commit();
                    break;
                case DEPARTURE:
                    flight.setDepartureDate(newDate);
                    flight.setDepartureTime(newTime);
                    session.getTransaction().commit();
                    break;
                default:
                    logger.info("Wrong date time type");
            }
        }
        else {
            logger.error("A row you are trying to update does not exist");
        }
    }

    @Override
    public void updatePrice(Session session, Integer updateFlightId, double newPrice) {
        if (isPresent(session,updateFlightId)){
            FlightsEntity flight = getById(session,updateFlightId).get();
            session.beginTransaction();
            flight.setPrice(newPrice);
            session.getTransaction().commit();
        }
        else{
            logger.error("A row you are trying to update does not exist");
        }
    }
}
