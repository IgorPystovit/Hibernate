package com.epam.igorpystovit.model.dao.implementations;

import com.epam.igorpystovit.model.dao.interfaces.CompaniesDAO;
import com.epam.igorpystovit.model.pojo.ClientsEntity;
import com.epam.igorpystovit.model.pojo.CompaniesEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unchecked")
public class CompaniesDAOImpl implements CompaniesDAO {


    @Override
    public List<CompaniesEntity> getAll(Session session) {
        Query<CompaniesEntity> query = session.createQuery("from CompaniesEntity ");
        session.beginTransaction();
        List <CompaniesEntity> companies = query.list();
        session.getTransaction().commit();
        return companies;
    }

    @Override
    public Optional<CompaniesEntity> getById(Session session, Integer id) {
        Query<CompaniesEntity> query = session.createQuery("from CompaniesEntity where id = : code");
        query.setParameter("code",id);
        Optional<CompaniesEntity> company = Optional.empty();
        if (!query.list().isEmpty()){
            company = Optional.ofNullable(query.list().get(0));
        }
        return company;
    }

    @Override
    public void create(Session session, CompaniesEntity entity) {
        if (!isPresent(session,entity.getId())){
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        }
        else {
            logger.info("You are trying to insert duplicate");
        }
    }

    @Override
    public void update(Session session, CompaniesEntity entity) {
        if (isPresent(session,entity.getId())){
            CompaniesEntity company = getById(session,entity.getId()).get();
            session.beginTransaction();
            company.setName(entity.getName());
            session.getTransaction().commit();
        }
        else {
            logger.info("A row you are trying to update does not exist");
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
