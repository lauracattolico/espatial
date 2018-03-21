package ie.espatial.controller.activity;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import ie.espatial.db.dao.FactoryDAO;
import ie.espatial.model.Airport;
import ie.espatial.service.AirportService;

public class AirportActivity implements Activity {

	private final static Logger logger = Logger.getLogger(AirportActivity.class);
	
	private final AirportService airportService;

	public AirportActivity() {
		airportService = new AirportService(FactoryDAO.buildAirportDAO());
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		logger.debug("execute");		
		logger.debug("req.getPathInfo(): " + req.getPathInfo());
		
		List<Airport> airports = airportService.retrieveActiveAirports();

		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = resp.getWriter();
		out.println(mapper.writeValueAsString(airports));
		
	}

}
