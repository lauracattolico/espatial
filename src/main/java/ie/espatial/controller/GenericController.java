package ie.espatial.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ie.espatial.controller.activity.Activity;
import ie.espatial.controller.activity.AirportActivity;
import ie.espatial.controller.activity.BookActivity;
import ie.espatial.controller.activity.DefaultActivity;
import ie.espatial.controller.activity.FlightActivity;

@SuppressWarnings("serial")
public class GenericController extends HttpServlet {
	
	private final static Logger logger = Logger.getLogger(GenericController.class);
	
	private HashMap<String, Activity> activities;
	
	@Override
	public void init() throws ServletException {
		super.init();
		activities = new HashMap<>();
		activities.put("/retrieve/activeAirports", new AirportActivity());
		activities.put("/retrieve/availableDates", new FlightActivity());
		activities.put("/search/flights", new FlightActivity());
		activities.put("/book/flight", new BookActivity());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug("doGet");		
		logger.debug("req.getPathInfo(): " + req.getPathInfo());
		Activity activity = activities.getOrDefault(req.getPathInfo(), new DefaultActivity());		
		activity.execute(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug("doPost");	
		logger.debug("req.getPathInfo(): " + req.getPathInfo());
		Activity activity = activities.getOrDefault(req.getPathInfo(), new DefaultActivity());		
		activity.execute(req, resp);
	}
	
}
