package ie.espatial.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

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

public class AirportServiceMockTest {
	
	@Mock
	private AirportDAO daoMock;
	
	@InjectMocks
	private AirportService airportService;
	
	@Before
    public void setUp() throws Exception {
         MockitoAnnotations.initMocks(this);
    }

	@Test
	public void testRetrieveActiveAirportsMocked() {
		List<Airport> airports = new ArrayList<Airport>();
		airports.add(new Airport());
		airports.add(new Airport());
		when(daoMock.retrieveActiveAirports()).thenReturn(airports);
		List<Airport> actualAirports = airportService.retrieveActiveAirports();
        assertThat(actualAirports, is(notNullValue()));
        assertThat(actualAirports, hasSize(2));
        assertThat(actualAirports.size(), is(2));

	}
}
