package com.epam.igorpystovit.model.dao.interfaces;

import com.epam.igorpystovit.model.pojo.PlanesCompaniesEntity;
import org.hibernate.Session;

public interface PlanesCompaniesDAO extends GeneralDAO<PlanesCompaniesEntity,Integer>{
    void updateSeatNum(Session session,Integer id, Integer newAvailableSeatsNum);
}
