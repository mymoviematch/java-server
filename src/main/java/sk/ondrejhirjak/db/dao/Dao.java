package sk.ondrejhirjak.db.dao;

import org.apache.ibatis.session.SqlSessionFactory;


public abstract class Dao<T> {

    private static Dao<?> instance;

    protected SqlSessionFactory sqlSessionFactory;


    public void init(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
        instance = this;
    }


    public static Dao<?> getInstance() {
        return instance;
    }
}
