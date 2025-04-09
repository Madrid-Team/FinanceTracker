interface FinanceTracker {

    fun add(transaction: Transaction): Boolean
    fun viewAllTransactions(): List<Transaction>
    fun editTransaction(transactionId: Int)
    fun deleteTransaction(transactionId: Int)
    fun getSummary(): Summary
}