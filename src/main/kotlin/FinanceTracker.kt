interface FinanceTracker {

    fun viewAllTransactions(transactions: List<Transaction>):String
    fun editTransaction(transactionId: Transaction): Result<Unit>
    fun getMonthlySummary(month: Int?, year: Int): Summary
    fun viewMostcategory(month: Int?, year: Int ,transaction : List<Transaction> ):String
    fun viewMinCategory(month: Int?, year: Int ,transaction : List<Transaction> ):String
    fun deleteTransaction(transactionId: Int):Boolean
    fun add(transaction: Transaction): Result<Unit>
 }


