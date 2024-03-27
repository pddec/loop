package loop.update.config;

import org.apache.commons.dbcp2.BasicDataSource;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

public class H2Connection {

    private final String url;
    private final String user;
    private final String password;
    private final String className;
    private final Integer minIdle;
    private final Integer maxIdle;
    private final Integer maxOpenPrepStmt;

    public H2Connection(String url, String user, String password,
                        String className, Integer minIdle,
                        Integer maxIdle, Integer maxOpenPrepStmt) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.className = className;
        this.minIdle = minIdle;
        this.maxIdle = maxIdle;
        this.maxOpenPrepStmt = maxOpenPrepStmt;
    }

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

    public static H2ConnectionBuilder builder(){
        return new H2ConnectionBuilder();
    }

    public static class H2ConnectionBuilder{
        private  String url;
        private  String user;
        private  String password;
        private  String className;
        private  Integer minIdle;
        private  Integer maxIdle;
        private  Integer maxOpenPrepStmt;

        public H2ConnectionBuilder url(String url) {
            this.url = url;
            return this;
        }

        public H2ConnectionBuilder user(String user) {
            this.user = user;
            return this;
        }

        public H2ConnectionBuilder password(String password) {
            this.password = password;
            return this;
        }

        public H2ConnectionBuilder className(String className) {
            this.className = className;
            return this;
        }

        public H2ConnectionBuilder minIdle(Integer minIdle) {
            this.minIdle = minIdle;
            return this;
        }

        public H2ConnectionBuilder maxIdle(Integer maxIdle) {
            this.maxIdle = maxIdle;
            return this;
        }

        public H2ConnectionBuilder maxOpenPrepStmt(Integer maxOpenPrepStmt) {
            this.maxOpenPrepStmt = maxOpenPrepStmt;
            return this;
        }

        public H2Connection build() {
            return new H2Connection(this.url, this.user, this.password, this.className,
                    this.minIdle, this.maxIdle, this.maxOpenPrepStmt);
        }

    }

}
