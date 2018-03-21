package ie.espatial.model;

public class Route {

	private int id;
	private int idAirportDeparture;
	private int idAirportArrival;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdAirportDeparture() {
		return this.idAirportDeparture;
	}

	public void setIdAirportDeparture(int idAirportDeparture) {
		this.idAirportDeparture = idAirportDeparture;
	}

	public int getIdAirportArrival() {
		return this.idAirportArrival;
	}

	public void setIdAirportArrival(int idAirportArrival) {
		this.idAirportArrival = idAirportArrival;
	}

}
