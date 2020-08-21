package Models;

import java.sql.Timestamp;

public class Transaction {
	private int Id;
	private int SenderId;
	private int ReceiverId;
	private Timestamp Date;
	private String Remarks;
	private Double Amount;
	private String TransactionType;
	
	public Transaction()
	{
		
	}
	public Transaction(int Id,int SenderId,int ReceiverId,Timestamp Date,String Remarks,Double Amount,String TransactionType)
	{
		this.setId(Id);
		this.setSenderId(SenderId);
		this.setReceiverId(ReceiverId);
		this.setRemarks(Remarks);
		this.setAmount(Amount);
		this.setDate(Date);
		this.setTransactionType(TransactionType);
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getSenderId() {
		return SenderId;
	}

	public void setSenderId(int senderId) {
		SenderId = senderId;
	}

	public int getReceiverId() {
		return ReceiverId;
	}

	public void setReceiverId(int receiverId) {
		ReceiverId = receiverId;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

	public Double getAmount() {
		return Amount;
	}

	public void setAmount(Double amount) {
		Amount = amount;
	}
	public Timestamp getDate() {
		return Date;
	}
	public void setDate(Timestamp date) {
		Date = date;
	}
	public String getTransactionType() {
		return TransactionType;
	}
	public void setTransactionType(String transactionType) {
		TransactionType = transactionType;
	}
}
