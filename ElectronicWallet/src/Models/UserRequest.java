package Models;

import java.sql.Timestamp;

public class UserRequest extends User{
	private int Id;
	private int UserId;
	private Double RequestBalance;
	private Timestamp RequestDate;
	private String RequestStatus;
	private String RequestType;
	
	public UserRequest() {
		
	}
	//constructor overloading
	public UserRequest(int Id,int UserId,Double RequestBalance,Timestamp RequestDate,String RequestStatus,String Name,String PhoneNumber,String RequestType) {
		this.setId(Id);
		this.setUserId(UserId);
		this.setRequestBalance(RequestBalance);
		this.setRequestDate(RequestDate);
		this.setRequestStatus(RequestStatus);
		
		super.setName(Name); //The super keyword refers to superclass (parent) objects. It is used to call superclass methods, and to access the superclass constructor. 
		super.setPhoneNumber(PhoneNumber);
		
		this.setRequestType(RequestType);
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getUserId() { //user class method override
		return UserId;
	}
	public void setUserId(int userId) { //user class method override
		UserId = userId;
	}
	public Double getRequestBalance() {
		return RequestBalance;
	}
	public void setRequestBalance(Double requestBalance) {
		RequestBalance = requestBalance;
	}
	public Timestamp getRequestDate() {
		return RequestDate;
	}
	public void setRequestDate(Timestamp requestDate) {
		RequestDate = requestDate;
	}
	public String getRequestStatus() {
		return RequestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		RequestStatus = requestStatus;
	}
	public String getRequestType() {
		return RequestType;
	}
	public void setRequestType(String requestType) {
		RequestType = requestType;
	}
}
