package Models;

public class User {
	private int Id;
	private String UserName;
	private String Password;
	private String Name;
	private String Address;
	private String PhoneNumber;
	private String Gender;
	private Double Balance;
	private String Status;
	
	public User() {
		
	}
	public User(String UserName,String Password,String Name,String Address,
			String PhoneNumber, String Gender, Double Balance, String Status)
	{
		this.setUserName(UserName);
		this.setPassword(Password);
		this.setName(Name);
		this.setAddress(Address);
		this.setPhoneNumber(PhoneNumber);
		this.setGender(Gender);
		this.setBalance(Balance);
		this.setStatus(Status);
	}
	
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public Double getBalance() {
		return Balance;
	}
	public void setBalance(Double balance) {
		Balance = balance;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
}
