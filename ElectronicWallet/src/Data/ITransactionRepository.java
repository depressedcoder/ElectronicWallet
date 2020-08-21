package Data;

import java.util.ArrayList;

import Models.Transaction;

public interface ITransactionRepository {
	Boolean InsertTransaction(Transaction transaction);
	ArrayList<Transaction> GetAllTransactions();
	Boolean DeleteTransactionById(int Id);
}
