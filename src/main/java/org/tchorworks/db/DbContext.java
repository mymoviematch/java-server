package org.tchorworks.db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.flywaydb.core.Flyway;
import org.tchorworks.conf.Configuration;
import org.tchorworks.db.dao.Dao;
import org.tchorworks.module.ServerModule;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;


public class DbContext implements ServerModule {

    private static final Logger LOGGER = Logger.getLogger(DbContext.class);

    private static final String DEFAULT_RESOURCE = "db/mybatis.xml";

    private String resource;

    private String versions;

    private Set<Dao<?>> daos = new HashSet<>();


    public DbContext(String resource, String versions) {
        this.resource = resource;
        this.versions = versions;
    }


    public DbContext(String resource) {
        this(resource, null);
    }


    public DbContext() {
        this(DEFAULT_RESOURCE, null);
    }


    @Override
    public void init(Configuration configuration) {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.info("Connecting to database url: '" + configuration.dbUrl + "' as user: '" + configuration.dbUsername + "'");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, buildDbProperties(configuration));

        migrateDb(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(), configuration);

        if (daos != null) {
            for (Dao<?> dao : daos) {
                dao.init(sqlSessionFactory);
            }
        }
    }


    @Override
    public void start() {
    }


    @Override
    public void stop() {
    }


    private Properties buildDbProperties(Configuration configuration) {
        Properties dbProperties = new Properties();

        dbProperties.setProperty("db.driver", configuration.dbDriver);
        dbProperties.setProperty("db.url", configuration.dbUrl);
        dbProperties.setProperty("db.username", configuration.dbUsername);
        dbProperties.setProperty("db.password", configuration.dbPassword);

        return dbProperties;
    }


    private void migrateDb(DataSource dataSource, Configuration configuration) {
        LOGGER.debug("dbAutoMigration: " + configuration.dbAutoMigration);

        if (configuration.dbAutoMigration) {
            Flyway flyway = new Flyway();

            if (versions != null) {
                flyway.setLocations(versions);
            }

            flyway.setDataSource(dataSource);

            flyway.migrate();
        }
    }


    public void addDao(Dao<?> dao) {
        // TODO: fail to add daos after initialized
        daos.add(dao);
    }


    public void addDaos(Set<Dao<?>> newDaos) {
        // TODO: fail to add daos after initialized
        daos.addAll(newDaos);
    }
}
