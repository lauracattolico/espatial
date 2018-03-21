package ie.espatial.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import ie.espatial.db.DataSource;

public class TripDAO {

	private final static Logger logger = Logger.getLogger(TripDAO.class);
	
	private static final String INSERT_TRIP = "INSERT INTO Trip (idUser) VALUES (1)";

	private final DataSource dataSource;

	public TripDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public int insertTrip(Connection conn) {
		int idTrip = 0;
		try (PreparedStatement statement = conn.prepareStatement(INSERT_TRIP, Statement.RETURN_GENERATED_KEYS)) {
			int affectedRows = statement.executeUpdate();
			if (affectedRows > 0) {
				try (ResultSet resultSet = statement.getGeneratedKeys()) {
					if (resultSet.next()) {
						idTrip = resultSet.getInt(1);
					}
				}
			}
		} catch (SQLException e) {
			logger.error(e);
		}

		return idTrip;
	}

}
