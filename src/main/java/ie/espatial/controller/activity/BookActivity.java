package ie.espatial.controller.activity;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import ie.espatial.service.BookService;

public class BookActivity implements Activity {

	private final static Logger logger = Logger.getLogger(BookActivity.class);
	
	private final BookService bookService;

	public BookActivity() {
		bookService = new BookService();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		logger.debug("execute");		
		logger.debug("req.getPathInfo(): " + req.getPathInfo());
		
		int idBookedFlight = bookService.bookFlight(req.getParameter("idFlight"));
		
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = resp.getWriter();
		out.println(mapper.writeValueAsString(idBookedFlight));
		
	}

}
