package espatial;

import java.util.List;

import ie.espatial.model.Airport;
import ie.espatial.service.AirportService;
import junit.framework.TestCase;

public class AirportServiceTest extends TestCase {

	public void testRetrieveActiveAirports() {
		AirportService airportService = new AirportService();
		List<Airport> airports = airportService.retrieveActiveAirports();
		assertNotNull(airports);
		assertFalse(airports.isEmpty());
		
	}

}
