package ie.espatial.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import ie.espatial.db.DataSource;

public class BookFlightDAO {

	private final static Logger logger = Logger.getLogger(BookFlightDAO.class);
	
	private static final String INSERT_BOOKEDFLIGHT = "INSERT INTO BookedFlight (idTrip, idFlight) VALUES (?, ?)";

	private final DataSource dataSource;

	public BookFlightDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public int insertBookedFlight(int idTrip, int idFlight, Connection conn) {
		int idBookedFlight = 0;
		try (PreparedStatement statement = conn.prepareStatement(INSERT_BOOKEDFLIGHT,
						Statement.RETURN_GENERATED_KEYS)) {
			statement.setInt(1, idTrip);
			statement.setInt(2, idFlight);
			int effectedRows = statement.executeUpdate();
			if (effectedRows > 0) {
				try (ResultSet resultSet = statement.getGeneratedKeys()) {
					if (resultSet.next()) {
						idBookedFlight = resultSet.getInt(1);
					}
				}
			}
		} catch (SQLException e) {
			logger.error(e);
		}

		return idBookedFlight;
	}

}
