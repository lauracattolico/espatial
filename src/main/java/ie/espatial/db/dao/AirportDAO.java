package ie.espatial.db.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ie.espatial.db.DataSource;
import ie.espatial.model.Airport;

public class AirportDAO {
	
	private final static Logger logger = Logger.getLogger(AirportDAO.class);
	
	private static final String SELECT_ACTIVE_AIRPORTS = "SELECT * FROM Airport WHERE active = 1";

	private final DataSource dataSource;

	public AirportDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Airport> retrieveActiveAirports() {
		List<Airport> airports = new ArrayList<>();
		try (Connection conn = this.dataSource.getConnection();
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(SELECT_ACTIVE_AIRPORTS)) {
			while(resultSet.next()) {
				Airport airport = new Airport();
				airport.setId(resultSet.getInt("id"));
				airport.setName(resultSet.getString("name"));
				airport.setCity(resultSet.getString("city"));
				airport.setActive(resultSet.getBoolean("active"));
				airports.add(airport);
			}
		} catch (SQLException e) {
			logger.error(e);
		}

		return airports;
	}

}
