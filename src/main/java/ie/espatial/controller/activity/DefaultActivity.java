package ie.espatial.controller.activity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class DefaultActivity implements Activity {

	private final static Logger logger = Logger.getLogger(DefaultActivity.class);
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug("execute");	
		resp.getOutputStream().println("Unknown activity");
	}

}
