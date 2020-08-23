package Data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.nit.instance.DatabaseConnection;

import Models.Transaction;
import Models.User;

public class TransactionRepository implements ITransactionRepository {

	private Connection con;
	private DatabaseConnection dbc;
	
	public TransactionRepository()
	{
		dbc = DatabaseConnection.getDatabaseConnection();
		con = dbc.getConnection();
	}
	@Override
	public Boolean InsertTransaction(Transaction transaction) {
		try {
			if(transaction!=null) {
				Statement smt =  con.createStatement();
				String query = "insert into Transaction " + " (SenderId, ReceiverId, Remarks, Amount,TransactionType)"
	                    + " values ('"+transaction.getSenderId()+"',"
	                    		+ " '"+transaction.getReceiverId()+"',"
	                    						+ "'"+transaction.getRemarks()+"',"
	                    							+ " "+transaction.getAmount()+",'"+transaction.getTransactionType()+"')";
				
				smt.execute(query);
				return true;
			}
		}catch(Exception ex)
		{
			System.out.println("Couldn't Inserted the User Info for "+ex);
		}
		return false;
	}

	@Override
	public Boolean DeleteTransactionById(int Id) {
		try {
			if(Id!=0) {
				Statement smt =  con.createStatement();
				String query = "DELETE FROM `Transaction` WHERE Id="+Id;
				smt.execute(query);
				return true;
			}
		}catch(Exception ex)
		{
			System.out.println("Couldn't Delete the Transaction for "+ex);
		}
		return false;
	}
	@Override
	public ArrayList<Transaction> GetAllTransactions() {
		ArrayList<Transaction> listOfTransaction = new ArrayList<Transaction>();
		try {
			Statement stmt = con.createStatement();
			String sql = "Select * from Transaction";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Transaction transaction = new Transaction(rs.getInt(1), rs.getInt(2), rs.getInt(3),rs.getTimestamp(4),rs.getString("Remarks"), rs.getDouble(6), rs.getString("TransactionType"));
				listOfTransaction.add(transaction);
			}
		}catch(Exception e) {
			System.out.println("Couldn't get List of Transactions for "+e);
		}
		return listOfTransaction;
	}
	@Override
	public ArrayList<Transaction> GetAllTransactionsBySenderId(int SenderId) {
		ArrayList<Transaction> listOfTransaction = new ArrayList<Transaction>();
		try {
			Statement stmt = con.createStatement();
			String sql = "Select * from Transaction where SenderId="+SenderId+" order by Date desc";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Transaction transaction = new Transaction(rs.getInt(1), rs.getInt(2), rs.getInt(3),rs.getTimestamp(4),rs.getString("Remarks"), rs.getDouble(6), rs.getString("TransactionType"));
				listOfTransaction.add(transaction);
			}
		}catch(Exception e) {
			System.out.println("Couldn't get List of Transactions for "+e);
		}
		return listOfTransaction;
	}
	@Override
	public ArrayList<Transaction> GetAllTransactionsByReceiverId(int ReceiverId) {
		ArrayList<Transaction> listOfTransaction = new ArrayList<Transaction>();
		try {
			Statement stmt = con.createStatement();
			String sql = "Select * from Transaction where ReceiverId="+ReceiverId+" order by Date desc";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Transaction transaction = new Transaction(rs.getInt(1), rs.getInt(2), rs.getInt(3),rs.getTimestamp(4),rs.getString("Remarks"), rs.getDouble(6), rs.getString("TransactionType"));
				listOfTransaction.add(transaction);
			}
		}catch(Exception e) {
			System.out.println("Couldn't get List of Transactions for "+e);
		}
		return listOfTransaction;
	}

}
