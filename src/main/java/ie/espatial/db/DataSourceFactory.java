package ie.espatial.db;

import org.apache.log4j.Logger;

import ie.espatial.configuration.ConfigurationProvider;

public class DataSourceFactory {
	
	private final static Logger logger = Logger.getLogger(DataSourceFactory.class);
	
	private static DataSourceFactory INSTANCE = new DataSourceFactory();
	
	private final DataSource dataSource;
	
	private DataSourceFactory() {
		try {
			dataSource = new DataSource(new ConfigurationProvider().getProperties());
		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		}
	}
	
	public static DataSourceFactory getInstance() {
		return INSTANCE;
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
}
