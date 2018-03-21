package ie.espatial.db.util;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import ie.espatial.db.DataSource;
import ie.espatial.db.DataSourceFactory;

public class UtilityDAO {

	private final static Logger logger = Logger.getLogger(UtilityDAO.class);
	
	private final DataSource dataSource;

	private String insert = "INSERT INTO Flight (idRouteTimetable, date, maxTicket, bookedTicket, price) VALUES (?, ?, ?, ?, ?)";

	public UtilityDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void fillFlight() {
		try (Connection conn = this.dataSource.getConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(insert)) {

			for (int i = 1; i <= 40; i++) {
				String day = "";
				for (int j = 1; j <= 30; j++) {
					day = "" + j;
					preparedStmt.setInt(1, i);
					if (j < 10) {
						day = "0" + day;
					}
					preparedStmt.setString(2, "2018-04-" + day);
					preparedStmt.setInt(3, 200);
					preparedStmt.setInt(4, 180);
					preparedStmt.setInt(5, 145);
					// preparedStmt.execute();
				}
			}
		} catch (SQLException e) {
			logger.error(e);
		}

	}

	public static void main(String[] args) throws PropertyVetoException, IOException {
		UtilityDAO utilityDAO = new UtilityDAO(DataSourceFactory.getInstance().getDataSource());
		utilityDAO.fillFlight();
	}

}
