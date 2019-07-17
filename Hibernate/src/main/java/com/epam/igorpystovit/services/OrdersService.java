package com.epam.igorpystovit.services;

import com.epam.igorpystovit.model.dao.implementations.OrdersDAOImpl;
import com.epam.igorpystovit.model.dao.interfaces.OrdersDAO;
import com.epam.igorpystovit.model.pojo.ClientsEntity;
import com.epam.igorpystovit.model.pojo.FlightsEntity;
import com.epam.igorpystovit.model.pojo.OrdersEntity;
import com.epam.igorpystovit.model.pojo.PlanesCompaniesEntity;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class OrdersService implements Service<OrdersEntity,Integer>, OrdersDAO {
    private static final OrdersDAOImpl ORDERS_DAO = new OrdersDAOImpl();
    private static final ClientService CLIENT_SERVICE = new ClientService();
    private static final FlightsService FLIGHTS_SERVICE = new FlightsService();

    @Override
    public List<OrdersEntity> getAll(Session session) {
        return ORDERS_DAO.getAll(session);
    }

    @Override
    public Optional<OrdersEntity> getById(Session session, Integer id) {
        return ORDERS_DAO.getById(session,id);
    }

    @Override
    public void create(Session session, OrdersEntity order) {
        if ((CLIENT_SERVICE.isPresent(session,order.getClientId())) &&
                (FLIGHTS_SERVICE.isPresent(session,order.getFlightId()))){
            PlanesCompaniesService planesCompaniesService = new PlanesCompaniesService();
            ClientsEntity client = CLIENT_SERVICE.getById(session,order.getClientId()).get();
            FlightsEntity flight = FLIGHTS_SERVICE.getById(session,order.getFlightId()).get();
            PlanesCompaniesEntity planeCompany = planesCompaniesService.getById(session,flight.getPlaneCompanyId()).get();
            if (isFundsSufficient(client,flight)){
                   if (isSeatsAvailable(planeCompany)){
                       double clientCash = client.getCash();
                       double flightPrice = flight.getPrice();
                       client.setCash(clientCash - flightPrice);
                       CLIENT_SERVICE.update(session,client);
                       int availableSeats = planesCompaniesService.getById(session,flight.getPlaneCompanyId()).get().getAvailableSeats();
                       planesCompaniesService.updateSeatNum(session,flight.getPlaneCompanyId(),availableSeats-1);
                       ORDERS_DAO.create(session,order);
                   }
                   else {
                       logger.warn("There is no available seats");
                   }
            }
            else {
                logger.warn("Funds insufficient");
            }
        }
        else{
            logger.error("Something went wrong while creating row");
        }

    }

    @Override
    public void updateFlightId(Session session, Integer updateId, Integer newFlightId) {
        if ((FLIGHTS_SERVICE.isPresent(session, newFlightId)) &&
                (ORDERS_DAO.isPresent(session, updateId))) {
            PlanesCompaniesService planesCompaniesService = new PlanesCompaniesService();
            OrdersEntity order = ORDERS_DAO.getById(session, updateId).get();
            ClientsEntity client = CLIENT_SERVICE.getById(session, order.getClientId()).get();
            FlightsEntity oldFlight = FLIGHTS_SERVICE.getById(session, order.getFlightId()).get();
            FlightsEntity newFlight = FLIGHTS_SERVICE.getById(session, newFlightId).get();
            PlanesCompaniesEntity planeCompany = planesCompaniesService.getById(session,newFlight.getPlaneCompanyId()).get();
            if (isFundsSufficient(client, oldFlight) && isSeatsAvailable(planeCompany)) {
                double clientCash = client.getCash();
                double price = newFlight.getPrice();
                client.setCash(clientCash - price);
                PlanesCompaniesEntity oldPlanesCompaniesEntity = planesCompaniesService.getById(session,oldFlight.getPlaneCompanyId()).get();
                PlanesCompaniesEntity newPlanesCompaniesEntity = planesCompaniesService.getById(session,newFlight.getPlaneCompanyId()).get();
                planesCompaniesService.updateSeatNum(session,oldPlanesCompaniesEntity.getId(), oldPlanesCompaniesEntity.getAvailableSeats() + 1);
                planesCompaniesService.updateSeatNum(session,newPlanesCompaniesEntity.getId(), newPlanesCompaniesEntity.getAvailableSeats() - 1);
                CLIENT_SERVICE.update(session,client);
                ORDERS_DAO.update(session,new OrdersEntity(updateId, client.getId(), newFlightId));
            } else {
                logger.error("Something went wrong while updating row");
            }
        }
    }

    @Override
    public void updateClientId(Session session, Integer updateId, Integer newClientId) {
        if ((CLIENT_SERVICE.isPresent(session,newClientId)) &&
                (ORDERS_DAO.isPresent(session,updateId))){
            OrdersEntity order = ORDERS_DAO.getById(session,updateId).get();
            FlightsEntity flight = FLIGHTS_SERVICE.getById(session,order.getFlightId()).get();
            ClientsEntity newClient = CLIENT_SERVICE.getById(session,newClientId).get();
            if (isFundsSufficient(newClient,flight)){
                double clientCash = newClient.getCash();
                double price = flight.getPrice();
                newClient.setCash(clientCash - price);
                CLIENT_SERVICE.update(session,newClient);
                ORDERS_DAO.update(session,new OrdersEntity(updateId,newClientId,flight.getId()));
            }
            else{
                logger.info("Funds insufficient");
            }
        }
        else {
            logger.error("Something went wrong while updating row");
        }
    }

    @Override
    public void update(Session session, OrdersEntity order) {
        if ((CLIENT_SERVICE.isPresent(session,order.getClientId())) &&
                (FLIGHTS_SERVICE.isPresent(session,order.getFlightId())) &&
                (ORDERS_DAO.isPresent(session,order.getId()))){
            OrdersEntity oldOrder = ORDERS_DAO.getById(session,order.getId()).get();
            ClientsEntity oldClient = CLIENT_SERVICE.getById(session,oldOrder.getClientId()).get();
            ClientsEntity newClient = CLIENT_SERVICE.getById(session,order.getClientId()).get();
            FlightsEntity oldFlight = FLIGHTS_SERVICE.getById(session,oldOrder.getFlightId()).get();
            FlightsEntity newFlight = FLIGHTS_SERVICE.getById(session,order.getFlightId()).get();

            if (oldClient.getId() != newClient.getId() && oldFlight.getId() != newFlight.getId()){
                updateFlightId(session,order.getId(),newFlight.getId());
                updateClientId(session,order.getId(),newClient.getId());
            }
            else if (oldClient.getId() != newClient.getId()){
                updateClientId(session,order.getId(),newClient.getId());
            }
            else if (oldFlight.getId() != newFlight.getId()){
                updateFlightId(session,order.getId(),newFlight.getId());
            }
        }
        else{
            logger.error("Something went wrong while updating row");
        }
    }

    @Override
    public void delete(Session session, Integer id) {
        if (ORDERS_DAO.isPresent(session,id)){
            OrdersEntity order = ORDERS_DAO.getById(session,id).get();
            ClientsEntity client = CLIENT_SERVICE.getById(session,order.getClientId()).get();
            FlightsEntity flight = FLIGHTS_SERVICE.getById(session,order.getFlightId()).get();
            PlanesCompaniesService planesCompaniesService = new PlanesCompaniesService();
            PlanesCompaniesEntity planesCompaniesEntity = planesCompaniesService.getById(session,flight.getPlaneCompanyId()).get();
            planesCompaniesService.updateSeatNum(session,planesCompaniesEntity.getId(),planesCompaniesEntity.getAvailableSeats() + 1);
            client.setCash(client.getCash() + flight.getPrice());
            CLIENT_SERVICE.update(session,client);
            ORDERS_DAO.delete(session,id);
        }
        else {
            logger.error("Something went wrong while deleting row");
        }
    }

    private boolean isFundsSufficient(ClientsEntity client, FlightsEntity flight){
        return client.getCash() >= flight.getPrice();
    }

    private boolean isSeatsAvailable(PlanesCompaniesEntity entity){
        return entity.getAvailableSeats() > 0;
    }
}
