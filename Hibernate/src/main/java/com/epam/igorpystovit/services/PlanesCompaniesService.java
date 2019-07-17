package com.epam.igorpystovit.services;

import com.epam.igorpystovit.model.dao.implementations.PlanesCompaniesDAOImpl;
import com.epam.igorpystovit.model.dao.interfaces.PlanesCompaniesDAO;
import com.epam.igorpystovit.model.pojo.PlanesCompaniesEntity;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class PlanesCompaniesService implements Service<PlanesCompaniesEntity,Integer>, PlanesCompaniesDAO {
    private static final PlanesCompaniesDAOImpl PLANES_COMPANIES_DAO = new PlanesCompaniesDAOImpl();
    private static final PlanesService PLANES_SERVICE = new PlanesService();
    private static final CompaniesService COMPANIES_SERVICE = new CompaniesService();

    @Override
    public List<PlanesCompaniesEntity> getAll(Session session) {
        return PLANES_COMPANIES_DAO.getAll(session);
    }

    @Override
    public Optional<PlanesCompaniesEntity> getById(Session session, Integer id) {
        return PLANES_COMPANIES_DAO.getById(session,id);
    }

    @Override
    public void create(Session session, PlanesCompaniesEntity entity) {
        if ((PLANES_SERVICE.isPresent(session,entity.getPlaneId())) &&
                (COMPANIES_SERVICE.isPresent(session,entity.getCompanyId()))){
            validateSeatNum(session,entity);
            PLANES_COMPANIES_DAO.create(session,entity);
        }
        else{
            logger.error("Something went wrong while creating row");
        }
    }

    @Override
    public void update(Session session, PlanesCompaniesEntity entity) {
        if ((PLANES_SERVICE.isPresent(session,entity.getPlaneId()) &&
                (COMPANIES_SERVICE.isPresent(session,entity.getCompanyId())))){
            validateSeatNum(session,entity);
            PLANES_COMPANIES_DAO.update(session,entity);
        }
        else{
            logger.error("Something went wrong while updating row");
        }
    }

    @Override
    public void updateSeatNum(Session session, Integer id, Integer newAvailableSeatsNum) {
        if (PLANES_COMPANIES_DAO.isPresent(session,id)){
            PlanesCompaniesEntity planeCompany = PLANES_COMPANIES_DAO.getById(session,id).get();
            session.beginTransaction();
            planeCompany.setAvailableSeats(newAvailableSeatsNum);
            validateSeatNum(session,planeCompany);
            session.getTransaction().commit();
        }
        else{
            logger.error("A row you are trying to update does not exist");
        }
    }

    @Override
    public void delete(Session session, Integer id) {
        PLANES_COMPANIES_DAO.delete(session,id);
    }

    private void validateSeatNum(Session session,PlanesCompaniesEntity entity){
        int availableSeatNum = entity.getAvailableSeats();
        int maxSeatNum = PLANES_SERVICE.getById(session,entity.getPlaneId()).get().getCapacity();
        if (maxSeatNum < availableSeatNum) {
            availableSeatNum = maxSeatNum;
        }
        else if (availableSeatNum < 0){
            availableSeatNum = 0;
        }
        entity.setAvailableSeats(availableSeatNum);
    }

}
