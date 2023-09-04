package application;

public class BookingModel {

	Integer id;
	String firstName;
	String lastName;
	String phone;
	String location;
	String email;
	String date;

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
		return  firstName;
	}
	public String getLastName() {
		return  lastName;
	}
	public String getPhone() {
		return  phone;
	}
	public String getLocation() {
		return  location;
	}
	public String getEmail() {
		return  email;
	}
	public String getDate() {
		return  date;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
