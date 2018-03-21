package ie.espatial.service;

import java.util.List;

import ie.espatial.db.dao.AirportDAO;
import ie.espatial.db.dao.FactoryDAO;
import ie.espatial.model.Airport;

public class AirportService {
	
	private final AirportDAO airportDAO;
	
	public AirportService() {
		airportDAO = FactoryDAO.buildAirportDAO();
	}

	public List<Airport> retrieveActiveAirports(){
		return airportDAO.retrieveActiveAirports();
	}
	
}
