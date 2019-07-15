package com.epam.igorpystovit.dao;

import com.epam.igorpystovit.model.ClientsEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ClientsDAOImpl{

    public List<ClientsEntity> getAllClients(Session session){
        List<ClientsEntity> clientsEntities = new ArrayList<>();
        session.beginTransaction();
        Query query = session.createQuery("from "+"ClientsEntity");
        for (Object client : query.list()){
            clientsEntities.add((ClientsEntity)client);
        }
        session.getTransaction().commit();
        return clientsEntities;
    }

    public void createClient(Session session,ClientsEntity newClient){
        session.beginTransaction();
        session.save(newClient);
        session.getTransaction().commit();
        newClient.setId(newClient.getId()+1);
    }

    public List<ClientsEntity> getById(Session session,int id){
        Query query = session.createQuery("from "+"ClientsEntity where id =: code");
        query.setParameter("code",id);
        session.beginTransaction();
        List<ClientsEntity> clientsEntity =  query.list();
        session.getTransaction().commit();
        return clientsEntity;
    }

    public void removeClient(Session session,int clientId){
        List<ClientsEntity> clients = getById(session,clientId);
        ClientsEntity client = clients.get(0);
        session.beginTransaction();
        session.remove(client);
        session.getTransaction().commit();
    }
}
