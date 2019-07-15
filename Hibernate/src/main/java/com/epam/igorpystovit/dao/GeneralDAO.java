package com.epam.igorpystovit.dao;

import java.util.List;

public interface GeneralDAO<T,ID> {

    List<T> getAll();
    T getById();
    void create(T entity);
    void update(T entity);
    void delete(ID id);
}
