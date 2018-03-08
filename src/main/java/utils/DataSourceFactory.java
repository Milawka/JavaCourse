package utils;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class DataSourceFactory {
    private static DataSource datasource = new DataSource();
    static {
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:postgresql://localhost:5432/library");
        p.setDriverClassName("org.postgresql.Driver");
        p.setUsername("postgres");
        p.setPassword("postgres");
        datasource.setPoolProperties(p);
    }

    /**
     * Get an object DataSource for connection to DB
     * @return dataSource
     */
    public static DataSource getDataSource() {
        return datasource;
    }
}
