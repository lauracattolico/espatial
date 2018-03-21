package ie.espatial.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ie.espatial.db.dao.FactoryDAO;
import ie.espatial.db.dao.FlightDAO;
import ie.espatial.model.Flight;
import ie.espatial.model.FlightResult;

public class FlightService {

	private final static Logger logger = Logger.getLogger(FlightService.class);

	private final FlightDAO flightDAO;

	public FlightService() {
		flightDAO = FactoryDAO.buildFlightDAO();
	}

	public List<String> retrieveAvailableDates() {
		return flightDAO.retrieveAvailableDates();
	}

	public List<FlightResult> searchFlights(String idAirportDeparture, String idAirportArrival, String departureDate,
			String arrivalDate) {
		List<FlightResult> flightResults = new ArrayList<>();
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date parsedDate = format.parse(departureDate);
			java.sql.Date sqlDateDeparture = new java.sql.Date(parsedDate.getTime());
			java.sql.Date sqlDateArrival = null;
			if (arrivalDate != null && !arrivalDate.trim().isEmpty()) {
				parsedDate = format.parse(arrivalDate);
				sqlDateArrival = new java.sql.Date(parsedDate.getTime());
			}
			List<Flight> flights = flightDAO.searchFlights(Integer.parseInt(idAirportDeparture),
					Integer.parseInt(idAirportArrival), sqlDateDeparture, sqlDateArrival);

			flightResults = buildFlightsResults(flights, format);

		} catch (Exception e) {
			logger.error(e);
		}
		return flightResults;
	}

	private List<FlightResult> buildFlightsResults(List<Flight> flights, SimpleDateFormat format) {
		Map<String, FlightResult> flightsMap = new LinkedHashMap<>();
		SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
		for (Flight flight : flights) {
			FlightResult flightResult = flightsMap.get(flight.getCode());
			if (flightResult == null) {
				flightResult = new FlightResult();
				flightResult.setId(flight.getId());
				flightResult.setIdRoute(flight.getIdRoute());
				flightResult.setDeparture(flight.getDeparture());
				flightResult.setArrival(flight.getArrival());
				flightResult.setCode(flight.getCode());
				flightResult.setDate(format.format(flight.getDate()));
				flightResult.setFlightTimeList(new ArrayList<>());

				flightsMap.put(flight.getCode(), flightResult);
			}

			FlightResult.FlightTime flightTime = new FlightResult.FlightTime();
			flightTime.setAvailableTicket(flight.getMaxTicket() - flight.getBookedTicket());
			flightTime.setHourDeparture(hourFormat.format(flight.getHourDeparture()));
			flightTime.setHourArrival(hourFormat.format(flight.getHourArrival()));
			flightTime.setPrice(String.valueOf(flight.getPrice()));

			flightResult.getFlightTimeList().add(flightTime);
		}

		return new ArrayList<>(flightsMap.values());
	}

}
