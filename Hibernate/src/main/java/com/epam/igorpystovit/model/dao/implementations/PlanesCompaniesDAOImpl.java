package com.epam.igorpystovit.model.dao.implementations;

import com.epam.igorpystovit.model.dao.interfaces.PlanesCompaniesDAO;
import com.epam.igorpystovit.model.pojo.CompaniesEntity;
import com.epam.igorpystovit.model.pojo.DateTimeParser;
import com.epam.igorpystovit.model.pojo.PlanesCompaniesEntity;
import com.epam.igorpystovit.model.pojo.PlanesEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unchecked")
public class PlanesCompaniesDAOImpl implements PlanesCompaniesDAO {
    @Override
    public List<PlanesCompaniesEntity> getAll(Session session) {
        Query<PlanesCompaniesEntity> query = session.createQuery("from PlanesCompaniesEntity ");
        session.beginTransaction();
        List<PlanesCompaniesEntity> planesCompanies = query.list();
        session.getTransaction().commit();
        return planesCompanies;
    }

    @Override
    public Optional<PlanesCompaniesEntity> getById(Session session, Integer id) {
        Query<PlanesCompaniesEntity> query = session.createQuery("from PlanesCompaniesEntity where id = : code");
        query.setParameter("code",id);
        Optional<PlanesCompaniesEntity> planesCompanies = Optional.empty();
        if (!query.list().isEmpty()){
            planesCompanies = Optional.ofNullable(query.list().get(0));
        }
        return planesCompanies;
    }


    @Override
    public void create(Session session, PlanesCompaniesEntity entity) {
        CompaniesDAOImpl companiesDAO = new CompaniesDAOImpl();
        PlanesDAOImpl planesDAO = new PlanesDAOImpl();
        if (!isPresent(session,entity.getId())){
            CompaniesEntity company = companiesDAO.getById(session,entity.getCompanyId()).get();
            PlanesEntity plane = planesDAO.getById(session,entity.getPlaneId()).get();
            session.beginTransaction();
            entity.setCompaniesByCompanyId(company);
            entity.setPlanesByPlaneId(plane);
            company.getPlanesCompaniesById().add(entity);
            plane.getPlanesCompaniesById().add(entity);
            session.save(entity);
            session.getTransaction().commit();
        }
        else{
            logger.info("You are trying to insert duplicate primary key");
        }
    }

    @Override
    public void update(Session session, PlanesCompaniesEntity entity) {
        CompaniesDAOImpl companiesDAO = new CompaniesDAOImpl();
        PlanesDAOImpl planesDAO = new PlanesDAOImpl();
        if (isPresent(session,entity.getId())){
            PlanesCompaniesEntity planeCompany = getById(session,entity.getId()).get();
            session.beginTransaction();
            planeCompany.setPlaneId(entity.getPlaneId());
            planeCompany.setAvailableSeats(entity.getAvailableSeats());
            planeCompany.setCompanyId(entity.getCompanyId());
            planeCompany.setCompaniesByCompanyId(companiesDAO.getById(session,entity.getCompanyId()).get());
            planeCompany.setPlanesByPlaneId(planesDAO.getById(session,entity.getPlaneId()).get());
            session.getTransaction().commit();
        }
        else {
            logger.info("A row you are trying to update does not exist");
        }
    }

    @Override
    public void updateSeatNum(Session session,Integer id, Integer newAvailableSeatsNum) {
        if (isPresent(session, id)){
            PlanesCompaniesEntity planeCompany = getById(session, id).get();
            session.beginTransaction();
            planeCompany.setAvailableSeats(newAvailableSeatsNum);
            session.getTransaction().commit();
        }
        else{
            logger.info("A row you are trying to update does not exist");
        }
    }

    @Override
    public void delete(Session session, Integer id) {
        CompaniesDAOImpl companiesDAO = new CompaniesDAOImpl();
        PlanesDAOImpl planesDAO = new PlanesDAOImpl();
        if (isPresent(session,id)){
            PlanesCompaniesEntity planeCompany = getById(session,id).get();
            CompaniesEntity company = companiesDAO.getById(session,planeCompany.getCompanyId()).get();
            PlanesEntity plane = planesDAO.getById(session,planeCompany.getPlaneId()).get();
            session.beginTransaction();
            company.getPlanesCompaniesById().remove(planeCompany);
            plane.getPlanesCompaniesById().remove(planeCompany);
            session.remove(planeCompany);
            session.getTransaction().commit();
        }
        else {
            logger.info("A row you are trying to delete does not exist");
        }
    }
}
