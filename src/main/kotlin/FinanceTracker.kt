interface FinanceTracker {

    fun add(transaction: Transaction)
    fun viewAllTransactions(): List<Transaction>
    fun editTransaction(transactionId: Int)
    fun deleteTransaction(transactionId: Int):Boolean
    fun getSummary(): Summary
}