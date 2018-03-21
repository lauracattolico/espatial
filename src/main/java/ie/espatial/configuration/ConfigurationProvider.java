package ie.espatial.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigurationProvider {

	private final static Logger logger = Logger.getLogger(ConfigurationProvider.class);
	
	private static final String CONFIGURATION_FILE = "/application.properties";
	
	private final Properties properties;
	
	public ConfigurationProvider() throws IOException {
		logger.debug(ConfigurationProvider.class.getResource(CONFIGURATION_FILE));
		
		InputStream inputStream = ConfigurationProvider.class.getResourceAsStream(CONFIGURATION_FILE);
		properties = new Properties();
		properties.load(inputStream);
		
		logger.debug(properties.getProperty("datasource.driverclass"));
		logger.debug(properties.getProperty("datasource.jdbcurl"));
		logger.debug(properties.getProperty("datasource.user"));
		logger.debug(properties.getProperty("datasource.password"));
		
	}

	public Properties getProperties() {
		return properties;
	}
	
	
}
