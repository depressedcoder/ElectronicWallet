package Models;

public class Transaction {
	private int Id;
	private int SenderId;
	private int ReceiverId;
	private String Date;
	private String Remarks;
	private Double Amount;
	
	public Transaction(int Id,int SenderId,int ReceiverId,String Date,String Remarks,Double Amount)
	{
		this.setId(Id);
		this.setSenderId(SenderId);
		this.setReceiverId(ReceiverId);
		this.setDate(Date);
		this.setRemarks(Remarks);
		this.setAmount(Amount);
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

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
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
}
