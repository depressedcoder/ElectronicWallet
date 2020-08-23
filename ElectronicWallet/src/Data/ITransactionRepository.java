package Data;

import java.util.ArrayList;

import Models.Transaction;

public interface ITransactionRepository {
	Boolean InsertTransaction(Transaction transaction);
	ArrayList<Transaction> GetAllTransactions();
	ArrayList<Transaction> GetAllTransactionsBySenderId(int SenderId);
	ArrayList<Transaction> GetAllTransactionsByReceiverId(int ReceiverId);
	Boolean DeleteTransactionById(int Id);
}
