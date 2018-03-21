package ie.espatial.db.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ie.espatial.db.DataSource;
import ie.espatial.model.Flight;

public class FlightDAO {
	
	private final static Logger logger = Logger.getLogger(FlightDAO.class);

	private static final String SELECT_AVAILABLE_DATES = "SELECT DISTINCT date FROM Flight ORDER BY date ASC";
	
	private static final String SELECT_SINGLE_FLIGHT = "SELECT f.id, f.idRouteTimetable, rt.idRoute, rt.code, dep.name AS departure, arr.name AS arrival, "
			+ "f.date, rt.hourDeparture, rt.hourArrival, f.price, f.maxTicket, f.bookedTicket " + "FROM Flight f "
			+ "JOIN RouteTimetable rt ON f.idRouteTimetable = rt.id " + "JOIN Route r ON rt.idRoute = r.id "
			+ "JOIN Airport dep ON r.idAirportDeparture = dep.id " + "JOIN Airport arr ON r.idAirportArrival = arr.id "
			+ "WHERE r.idAirportDeparture = ? AND r.idAirportArrival = ? AND f.date = ? ";
	
	private static final String ORDER_BY_FLIGHT = " ORDER BY date, hourDeparture";
	
	private static final String UNION = " UNION ";
	
	private static final String UPDATE_BOOKEDTICKET = "UPDATE Flight set bookedTicket = (bookedTicket + 1) WHERE id = ?";

	private final DataSource dataSource;

	public FlightDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<String> retrieveAvailableDates() {
		List<String> availableDates = new ArrayList<>();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		try (Connection conn = this.dataSource.getConnection();
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(SELECT_AVAILABLE_DATES)) {
			while (resultSet.next()) {
				String date = df.format(resultSet.getDate("date"));
				availableDates.add(date);
			}
		} catch (SQLException e) {
			logger.error(e);
		}

		return availableDates;
	}

	public List<Flight> searchFlights(int idAirportDeparture, int idAirportArrival, Date sqlDateDeparture,
			Date sqlDateArrival) {
		List<Flight> singleFlights = new ArrayList<>();
		String searchQuery = "";
		if (sqlDateArrival != null) {
			searchQuery = SELECT_SINGLE_FLIGHT + UNION + SELECT_SINGLE_FLIGHT + ORDER_BY_FLIGHT;
		} else {
			searchQuery = SELECT_SINGLE_FLIGHT + ORDER_BY_FLIGHT;
		}

		try (Connection conn = this.dataSource.getConnection();
				PreparedStatement statement = conn.prepareStatement(searchQuery)) {
			int parameterIndex = 0;
			statement.setInt(++parameterIndex, idAirportDeparture);
			statement.setInt(++parameterIndex, idAirportArrival);
			statement.setDate(++parameterIndex, sqlDateDeparture);
			if (sqlDateArrival != null) {
				statement.setInt(++parameterIndex, idAirportArrival);
				statement.setInt(++parameterIndex, idAirportDeparture);
				statement.setDate(++parameterIndex, sqlDateArrival);
			}
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Flight flight = new Flight();
					flight.setId(resultSet.getInt("id"));
					flight.setCode(resultSet.getString("code"));
					flight.setIdRouteTimetable(resultSet.getInt("idRouteTimetable"));
					flight.setIdRoute(resultSet.getInt("idRoute"));
					flight.setDeparture(resultSet.getString("departure"));
					flight.setArrival(resultSet.getString("arrival"));
					flight.setDate(resultSet.getDate("date"));
					flight.setHourDeparture(resultSet.getTime("hourDeparture"));
					flight.setHourArrival(resultSet.getTime("hourArrival"));
					flight.setPrice(resultSet.getDouble("price"));
					flight.setMaxTicket(resultSet.getInt("maxTicket"));
					flight.setBookedTicket(resultSet.getInt("bookedTicket"));
					singleFlights.add(flight);
				}
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return singleFlights;
	}
	
	
	public boolean updateBookedTicket(int idFlight, Connection conn) {
		try (PreparedStatement statement = conn.prepareStatement(UPDATE_BOOKEDTICKET)) {
			statement.setInt(1, idFlight);
			int effectedRows = statement.executeUpdate();
			return effectedRows > 0;
		} catch (SQLException e) {
			logger.error(e);
		}

		return false;
	}

}
