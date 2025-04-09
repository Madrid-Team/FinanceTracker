interface FinanceTracker {

    fun add(transaction: Transaction)
    fun viewAllTransactions(transactions: List<Transaction>):String
    fun editTransaction(transactionId: Int)
    fun deleteTransaction(transactionId: Int)
    fun getMonthSummary(month: Int, year: Int): Summary
}