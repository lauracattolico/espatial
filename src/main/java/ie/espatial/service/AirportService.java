package ie.espatial.service;

import java.util.List;

import ie.espatial.db.dao.AirportDAO;
import ie.espatial.model.Airport;

public class AirportService {
	
	private final AirportDAO airportDAO;
	
	public AirportService(AirportDAO airportDAO) {
		this.airportDAO = airportDAO;
	}

	public List<Airport> retrieveActiveAirports(){
		return airportDAO.retrieveActiveAirports();
	}
	
}
