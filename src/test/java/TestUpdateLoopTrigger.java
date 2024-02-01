import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import loop.update.config.H2Connection;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestUpdateLoopTrigger {
	
	private BasicDataSource dataSource;

	@BeforeAll
	public void init() throws IOException {
	
			 final InputStream input = new FileInputStream("src/test/resources/application.properties");
	         final Properties prop = new Properties();
			 prop.load(input);
			 
			 this.dataSource = H2Connection.builder()
					 .maxIdle(Integer.valueOf((String)prop.get("datasource.idle.max")))
					 .minIdle(Integer.valueOf((String)prop.get("datasource.idle.min")))
					 .maxOpenPrepStmt(Integer.valueOf((String)prop.get("datasource.prepStmt.max")))
					 .className((String)prop.get("datasource.driver-class-name"))
					 .url((String)prop.get("datasource.url"))
					 .password((String)prop.get("datasource.password"))
					 .user((String)prop.get("datasource.username"))
					 .build().dataSource();
	}
	
	@Test
	public void test_loop_update() throws SQLException {
		this.dataSource.getConnection();
	}

}
