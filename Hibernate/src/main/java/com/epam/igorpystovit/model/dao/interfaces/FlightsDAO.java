package com.epam.igorpystovit.model.dao.interfaces;

import com.epam.igorpystovit.model.dao.implementations.FlightDateTimeType;
import com.epam.igorpystovit.model.pojo.FlightsEntity;
import org.hibernate.Session;

public interface FlightsDAO extends GeneralDAO<FlightsEntity,Integer>{
    void updateDateTime(Session session,Integer updateFlightId, String newDate, String newTime, FlightDateTimeType dateTimeType);
    void updatePrice(Session session,Integer updateFlightId,double newPrice);
}
