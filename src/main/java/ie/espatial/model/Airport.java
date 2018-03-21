package ie.espatial.model;

public class Airport {

	private int id;
	private String name;
	private String city;
	private boolean active;
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return this.city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public boolean isActive() {
		return this.active;
	}
	public void setActive(boolean isActive) {
		this.active = isActive;
	}
	
}
