
interface FinanceTracker {

    fun add(transaction: Transaction)
    fun viewAllTransactions(): List<Transaction>
    fun editTransaction(
        transaction: Transaction
    ): Result<Unit>

    fun deleteTransaction(transactionId: Int)
    fun getSummary(): Summary
}