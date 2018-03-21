package ie.espatial.controller.activity;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import ie.espatial.model.FlightResult;
import ie.espatial.service.FlightService;

public class FlightActivity implements Activity {

	private final static Logger logger = Logger.getLogger(FlightActivity.class);
	
	private final FlightService flightService;

	public FlightActivity() {
		flightService = new FlightService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		logger.debug("execute");		
		logger.debug("req.getPathInfo(): " + req.getPathInfo());
		
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = resp.getWriter();
		
		switch (req.getPathInfo()) {
		case "/retrieve/availableDates":
			List<String> availableDates = flightService.retrieveAvailableDates();
			out.println(mapper.writeValueAsString(availableDates));
			break;
		case "/search/flights":
			String arrivalDate = null;
			if (req.getParameter("dateTo") != null) {
				arrivalDate = req.getParameter("dateTo");
			}
			List<FlightResult> flights = flightService.searchFlights(req.getParameter("airportFrom"), req.getParameter("airportTo"), 
					req.getParameter("dateFrom"), arrivalDate);
			out.println(mapper.writeValueAsString(flights));
			break;

		default:
			break;
		}
		
		
	}

}
