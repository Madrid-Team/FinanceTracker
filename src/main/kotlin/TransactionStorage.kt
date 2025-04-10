interface TransactionStorage {
    fun saveTransactions(transactions: List<Transaction>)
    fun loadTransactions(): List<Transaction>
}