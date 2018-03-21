package ie.espatial.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import ie.espatial.db.DataSourceFactory;
import ie.espatial.db.dao.BookFlightDAO;
import ie.espatial.db.dao.FlightDAO;
import ie.espatial.db.dao.TripDAO;

public class BookService {

	private final static Logger logger = Logger.getLogger(BookService.class);
	
	private final TripDAO tripDAO;
	private final BookFlightDAO bookFlightDAO;
	private final FlightDAO flightDAO;

	public BookService(TripDAO tripDAO, BookFlightDAO bookFlightDAO, FlightDAO flightDAO) {
		this.tripDAO = tripDAO;
		this.bookFlightDAO = bookFlightDAO;
		this.flightDAO = flightDAO;
	}

	public int bookFlight(String idFlight) {
		if (idFlight == null || idFlight.trim().equals("")) {
			return 0;
		}
		
		Connection conn = null;
		try {
			conn = DataSourceFactory.getInstance().getDataSource().getConnection();
			conn.setAutoCommit(false);
			int idTrip = tripDAO.insertTrip(conn);
			if (idTrip == 0) {
				return 0;
			}
			int idBookedFlight = bookFlightDAO.insertBookedFlight(idTrip, Integer.parseInt(idFlight), conn);
			if (idBookedFlight == 0) {
				return 0;
			}
			if(!flightDAO.updateBookedTicket(Integer.parseInt(idFlight), conn)) {
				return 0;
			}
			
			conn.commit();
			return idBookedFlight;
		}
		catch (SQLException e) {
			logger.error(e);
			try {
				conn.rollback();
			} catch (SQLException ignore) {
			}
		}
		finally {
			try {
				conn.setAutoCommit(true);
				conn.close();
			} catch (SQLException ignore) {
			}
		}
		return 0;
	}

}
