package com.myhibernate.dao;

import java.util.List;

public interface GenericRepository<T, ID> {
    List<T> findAll();

   T create(T t);

    T read(ID ID);

    T update(T t);

    void delete(T t);
}

