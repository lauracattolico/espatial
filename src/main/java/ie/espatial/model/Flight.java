package ie.espatial.model;

import java.sql.Time;
import java.util.Date;

public class Flight {

	private int id;
	private int idRouteTimetable;
	private Date date;
	private int maxTicket;
	private int bookedTicket;
	private double price;

	private String code;
	private String departure;
	private String arrival;
	private Time hourDeparture;
	private Time hourArrival;
	private int idRoute;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdRouteTimetable() {
		return this.idRouteTimetable;
	}

	public void setIdRouteTimetable(int idRouteTimetable) {
		this.idRouteTimetable = idRouteTimetable;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getMaxTicket() {
		return this.maxTicket;
	}

	public void setMaxTicket(int maxTicket) {
		this.maxTicket = maxTicket;
	}

	public int getBookedTicket() {
		return this.bookedTicket;
	}

	public void setBookedTicket(int bookedTicket) {
		this.bookedTicket = bookedTicket;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public int getIdRoute() {
		return this.idRoute;
	}

	public void setIdRoute(int idRoute) {
		this.idRoute = idRoute;
	}
	

}
