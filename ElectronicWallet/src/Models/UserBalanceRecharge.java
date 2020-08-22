package Models;

import java.sql.Timestamp;

public class UserBalanceRecharge {
	private int Id;
	private int UserId;
	private Double RechargeBalance;
	private Timestamp RechargeDate;
	private String RechargeStatus;
	
	public UserBalanceRecharge() {
		
	}
	//constructor overloading
	public UserBalanceRecharge(int Id,int UserId,Double RechargeBalance,Timestamp RechargeDate,String RechargeStatus) {
		this.setId(Id);
		this.setUserId(UserId);
		this.setRechargeBalance(RechargeBalance);
		this.setRechargeDate(RechargeDate);
		this.setRechargeStatus(RechargeStatus);
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
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
}
