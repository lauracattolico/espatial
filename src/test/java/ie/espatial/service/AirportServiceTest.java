package ie.espatial.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import ie.espatial.db.dao.FactoryDAO;
import ie.espatial.model.Airport;
import ie.espatial.service.AirportService;

public class AirportServiceTest {

	@Test
	public void testRetrieveActiveAirports() {
		AirportService airportService = new AirportService(FactoryDAO.buildAirportDAO());
		List<Airport> airports = airportService.retrieveActiveAirports();
		assertNotNull(airports);
		assertFalse(airports.isEmpty());
	}

}
