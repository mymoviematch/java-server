package com.mymoviematch.simpleserver.db.dao;

import org.apache.ibatis.session.SqlSessionFactory;

import java.util.HashMap;
import java.util.Map;


public abstract class Dao<T> {

    private static Map<Class<?>, Dao<?>> daos = new HashMap<>();

    protected SqlSessionFactory sqlSessionFactory;


    public void init(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
        daos.put(this.getClass(), this);
    }


    public static <T> T getInstance(Class<?> daoClass) {
        return (T) daos.get(daoClass);
    }
}
