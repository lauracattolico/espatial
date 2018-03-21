package espatial;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ie.espatial.db.dao.AirportDAO;
import ie.espatial.model.Airport;
import ie.espatial.service.AirportService;

public class AirportServiceTest2 {
	
	@Mock
	private AirportDAO daoMock;
	
	@InjectMocks
	private AirportService airportService;
	
	@Before
    public void setUp() throws Exception {
         MockitoAnnotations.initMocks(this);
    }


	@Test
	public void testRetrieveActiveAirports() {
		AirportService service = new AirportService();
		List<Airport> airports = service.retrieveActiveAirports();
		assertNotNull(airports);
		assertFalse(airports.isEmpty());
	}


	@Test
	public void testRetrieveActiveAirportsMocked() {
		when(daoMock.retrieveActiveAirports()).thenReturn(new ArrayList<Airport>());
        assertThat(airportService.retrieveActiveAirports(), is(notNullValue()));
	}
}
