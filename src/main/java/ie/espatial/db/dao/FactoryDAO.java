package ie.espatial.db.dao;

import ie.espatial.db.DataSourceFactory;

public class FactoryDAO {
	
	public static AirportDAO buildAirportDAO() {
		return new AirportDAO(DataSourceFactory.getInstance().getDataSource());
	}
	
	public static FlightDAO buildFlightDAO() {
		return new FlightDAO(DataSourceFactory.getInstance().getDataSource());
	}
	
	public static TripDAO buildTripDAO() {
		return new TripDAO(DataSourceFactory.getInstance().getDataSource());
	}
	
	public static BookFlightDAO buildBookFlightDAO() {
		return new BookFlightDAO(DataSourceFactory.getInstance().getDataSource());
	}
}
