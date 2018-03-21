package ie.espatial.db;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSource {

	private ComboPooledDataSource comboPooledDataSource;

	public DataSource(Properties properties) throws PropertyVetoException {
		comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setDriverClass(properties.getProperty("datasource.driverclass"));
		comboPooledDataSource.setJdbcUrl(properties.getProperty("datasource.jdbcurl"));
		comboPooledDataSource.setUser(properties.getProperty("datasource.user"));
		comboPooledDataSource.setPassword(properties.getProperty("datasource.password"));

		// comboPooledDataSource.setMinPoolSize(5);
		// comboPooledDataSource.setAcquireIncrement(5);
		// comboPooledDataSource.setMaxPoolSize(20);
	}

	public Connection getConnection() throws SQLException {
		return comboPooledDataSource.getConnection();
	}

}
