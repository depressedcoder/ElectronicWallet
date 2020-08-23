package Models;

import java.sql.Timestamp;

public class UserBalanceRecharge extends User{
	private int Id;
	private int UserId;
	private Double RechargeBalance;
	private Timestamp RechargeDate;
	private String RechargeStatus;
	private String Name;
	private String PhoneNumber;
	
	
	public UserBalanceRecharge() {
		
	}
	//constructor overloading
	public UserBalanceRecharge(int Id,int UserId,Double RechargeBalance,Timestamp RechargeDate,String RechargeStatus,String Name,String PhoneNumber) {
		this.setId(Id);
		this.setUserId(UserId);
		this.setRechargeBalance(RechargeBalance);
		this.setRechargeDate(RechargeDate);
		this.setRechargeStatus(RechargeStatus);
		this.Name = Name;
		this.PhoneNumber = PhoneNumber;
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
	public Double getRechargeBalance() {
		return RechargeBalance;
	}
	public void setRechargeBalance(Double rechargeBalance) {
		RechargeBalance = rechargeBalance;
	}
	public Timestamp getRechargeDate() {
		return RechargeDate;
	}
	public void setRechargeDate(Timestamp rechargeDate) {
		RechargeDate = rechargeDate;
	}
	public String getRechargeStatus() {
		return RechargeStatus;
	}
	public void setRechargeStatus(String rechargeStatus) {
		RechargeStatus = rechargeStatus;
	}
	public String getName() { //user class method override
		return Name; 
	}
	public void setName(String name) { //user class method override
		Name = name;
	}
	public String getPhoneNumber() { //user class method override
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) { //user class method override
		PhoneNumber = phoneNumber;
	}
}
