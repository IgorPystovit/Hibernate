package com.epam.igorpystovit.services;

import com.epam.igorpystovit.model.dao.implementations.FlightDateTimeType;
import com.epam.igorpystovit.model.dao.implementations.FlightsDAOImpl;
import com.epam.igorpystovit.model.dao.interfaces.FlightsDAO;
import com.epam.igorpystovit.model.pojo.FlightsEntity;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightsService implements Service<FlightsEntity,Integer>, FlightsDAO {
    private static final FlightsDAOImpl FLIGHTS_DAO = new FlightsDAOImpl();
    private static final TownsService TOWNS_SERVICE = new TownsService();
    private static final PlanesCompaniesService PLANES_COMPANIES_SERVICE = new PlanesCompaniesService();

    @Override
    public List<FlightsEntity> getAll(Session session) {
        return FLIGHTS_DAO.getAll(session);
    }

    @Override
    public Optional<FlightsEntity> getById(Session session, Integer id) {
        return FLIGHTS_DAO.getById(session,id);
    }


    @Override
    public void create(Session session, FlightsEntity entity) {
        if ((PLANES_COMPANIES_SERVICE.isPresent(session,entity.getPlaneCompanyId())) &&
                (TOWNS_SERVICE.isPresent(session,entity.getDepartureTownId()))&&
                        (TOWNS_SERVICE.isPresent(session,entity.getArrivalTownId()))){
            if (entity.getDepartureTownId() == entity.getArrivalTownId()){
                logger.info("Departure town and arrival town are the same");
                return;
            }
            if (isPlaneCompanyIdPresent(session,entity.getPlaneCompanyId())){
                logger.info("This plane is already be takenn ");
                return;
            }
            FLIGHTS_DAO.create(session,entity);
        }
        else{
            logger.error("Something went wrong while creating row");
        }
    }

    @Override
    public void updateDateTime(Session session, Integer updateFlightId, String newDate, String newTime, FlightDateTimeType dateTimeType) {
        FLIGHTS_DAO.updateDateTime(session,updateFlightId,newDate,newTime,dateTimeType);
    }

    @Override
    public void updatePrice(Session session, Integer updateFlightId, double newPrice) {
        FLIGHTS_DAO.updatePrice(session, updateFlightId, newPrice);
    }

    @Override
    public void update(Session session, FlightsEntity entity) {
        if ((PLANES_COMPANIES_SERVICE.isPresent(session,entity.getPlaneCompanyId())) &&
                (TOWNS_SERVICE.isPresent(session,entity.getDepartureTownId())) &&
                (TOWNS_SERVICE.isPresent(session,entity.getArrivalTownId())) &&
                (FLIGHTS_DAO.isPresent(session,entity.getId()))){
            FlightsEntity oldFlight = FLIGHTS_DAO.getById(session,entity.getId()).get();
            if (entity.getDepartureTownId() == entity.getArrivalTownId()){
                logger.info("Departure town and arrival town are the same");
                return;
            }
            if (oldFlight.getPlaneCompanyId() != entity.getPlaneCompanyId()){
                if (isPlaneCompanyIdPresent(session,entity.getPlaneCompanyId())){
                    logger.error("This plane is already be taken");
                    return;
                }
            }
            FLIGHTS_DAO.update(session,entity);
        }
        else{
            logger.error("Something went wrong while updating row");
        }
    }

    @Override
    public void delete(Session session, Integer id) {
        FLIGHTS_DAO.delete(session,id);
    }

    private boolean isPlaneCompanyIdPresent(Session session,int planeCompanyId){
        List<FlightsEntity> flights = FLIGHTS_DAO.getAll(session);
        for (FlightsEntity tempFlight : new ArrayList<>(flights)){
            if (tempFlight.getPlaneCompanyId() != planeCompanyId){
                flights.remove(tempFlight);
            }
        }
        return !flights.isEmpty();
    }
}
