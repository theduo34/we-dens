package application;

public class BookingModel {

	private Integer id;
	private String firstName;
	private String lastName;
	private String phone;
	private String location;
	private String email;
	private String date;

	public BookingModel(Integer id,String firstName, String lastName, String phone, String location,String email, String date) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.location = location;
		this.email = email;
		this.date = date;

	}

	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getPhone() {
		return phone;
	}
	public String getLocation() {
		return location;
	}
	public String getEmail() {
		return email;
	}
	public String getDate() {
		return date;
	}

	
}
