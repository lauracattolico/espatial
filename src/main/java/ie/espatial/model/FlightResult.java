package ie.espatial.model;

import java.util.List;

public class FlightResult {

	private int id;
	private int idRoute;
	private String departure;
	private String arrival;
	private String code;
	private String date;
	private List<FlightTime> flightTimeList;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdRoute() {
		return this.idRoute;
	}

	public void setIdRoute(int idRoute) {
		this.idRoute = idRoute;
	}

	public String getDeparture() {
		return this.departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return this.arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<FlightTime> getFlightTimeList() {
		return this.flightTimeList;
	}

	public void setFlightTimeList(List<FlightTime> flightTimeList) {
		this.flightTimeList = flightTimeList;
	}

	public static class FlightTime {
		private String hourDeparture;
		private String hourArrival;
		private String price;
		private int availableTicket;

		public String getHourDeparture() {
			return this.hourDeparture;
		}

		public void setHourDeparture(String hourDeparture) {
			this.hourDeparture = hourDeparture;
		}

		public String getHourArrival() {
			return this.hourArrival;
		}

		public void setHourArrival(String hourArrival) {
			this.hourArrival = hourArrival;
		}

		public String getPrice() {
			return this.price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public int getAvailableTicket() {
			return this.availableTicket;
		}

		public void setAvailableTicket(int availableTicket) {
			this.availableTicket = availableTicket;
		}

	}

}
