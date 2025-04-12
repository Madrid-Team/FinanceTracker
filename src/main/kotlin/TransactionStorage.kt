
interface TransactionStorage {
    fun saveTransactions(transactions: Transaction)
    fun loadTransactions(): List<Transaction>
    fun deleteTransactionFromFile(transactionId: Int): String

}