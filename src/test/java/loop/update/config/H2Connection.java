package loop.update.config;

import org.apache.commons.dbcp2.BasicDataSource;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class H2Connection {

    private final String url;
    private final String user;
    private final String password;
    private final String className;
    private final Integer minIdle;
    private final Integer maxIdle;
    private final Integer maxOpenPrepStmt;

   
    public BasicDataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        if (ds.getDriverClassName() == null || ds.getDriverClassName().equals("")) {
            ds.setUrl(this.url);
            ds.setUsername(this.user);
            ds.setPassword(this.password);
            ds.setDriverClassName(this.className);
            ds.setMinIdle(this.minIdle);
            ds.setMaxIdle(this.maxIdle);
            ds.setMaxOpenPreparedStatements(maxOpenPrepStmt);
        }
        return ds;
    }

}
