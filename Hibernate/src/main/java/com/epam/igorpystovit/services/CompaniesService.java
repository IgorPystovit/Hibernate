package com.epam.igorpystovit.services;

import com.epam.igorpystovit.model.dao.implementations.CompaniesDAOImpl;
import com.epam.igorpystovit.model.dao.interfaces.CompaniesDAO;
import com.epam.igorpystovit.model.pojo.CompaniesEntity;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class CompaniesService implements Service<CompaniesEntity,Integer>, CompaniesDAO {
    private static final CompaniesDAOImpl COMPANIES_DAO = new CompaniesDAOImpl();

    @Override
    public List<CompaniesEntity> getAll(Session session) {
        return COMPANIES_DAO.getAll(session);
    }

    @Override
    public Optional<CompaniesEntity> getById(Session session, Integer id) {
        return COMPANIES_DAO.getById(session,id);
    }

    @Override
    public void create(Session session, CompaniesEntity entity) {
        COMPANIES_DAO.create(session,entity);
    }

    @Override
    public void update(Session session, CompaniesEntity entity) {
        COMPANIES_DAO.update(session,entity);
    }

    @Override
    public void delete(Session session, Integer id) {
        COMPANIES_DAO.delete(session,id);
    }
}
