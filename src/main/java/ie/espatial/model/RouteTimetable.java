package ie.espatial.model;

import java.sql.Time;

public class RouteTimetable {

	private int id;
	private int idRoute;
	private Time hourDeparture;
	private Time hourArrival;
	private String code;

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

	public Time getHourDeparture() {
		return this.hourDeparture;
	}

	public void setHourDeparture(Time hourDeparture) {
		this.hourDeparture = hourDeparture;
	}

	public Time getHourArrival() {
		return this.hourArrival;
	}

	public void setHourArrival(Time hourArrival) {
		this.hourArrival = hourArrival;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	

}
