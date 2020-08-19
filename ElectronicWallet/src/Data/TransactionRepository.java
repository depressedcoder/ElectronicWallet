package Data;

import java.sql.Connection;
import java.sql.Statement;

import org.nit.instance.DatabaseConnection;

import Models.Transaction;

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
				String query = "insert into Transaction " + " (SenderId, ReceiverId, Date, Remarks, Amount)"
	                    + " values ('"+transaction.getSenderId()+"',"
	                    		+ " '"+transaction.getReceiverId()+"',"
	                    				+ " '"+transaction.getDate()+"',"
	                    						+ "'"+transaction.getRemarks()+"',"
	                    							+ " "+transaction.getAmount()+")";
				
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

}
