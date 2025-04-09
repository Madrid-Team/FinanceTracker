interface FinanceTracker {

    fun add(transaction: Transaction)
    fun viewAllTransactions(transactions: List<Transaction>):String
    fun editTransaction(transactionId: Int)
    fun deleteTransaction(transactionId: Int)
    fun getMonthlySummary(month: Int?, year: Int): Summary
    fun viewMostcategory(month: Int?, year: Int ,transaction : List<Transaction> ):String
}