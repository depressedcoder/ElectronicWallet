package Data;

import Models.Transaction;

public interface ITransactionRepository {
	Boolean InsertTransaction(Transaction transaction);
	Boolean DeleteTransactionById(int Id);
}
