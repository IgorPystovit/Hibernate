package com.epam.igorpystovit.model.services;

import com.epam.igorpystovit.model.pojo.PlanesEntity;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class PlanesService implements Service<PlanesEntity,Integer>{
    @Override
    public List<PlanesEntity> getAll(Session session) {
        return null;
    }

    @Override
    public Optional<PlanesEntity> getById(Session session, Integer integer) {
        return Optional.empty();
    }

    @Override
    public void create(Session session, PlanesEntity entity) {

    }

    @Override
    public void update(Session session, PlanesEntity entity) {

    }

    @Override
    public void delete(Session session, Integer integer) {

    }
}
