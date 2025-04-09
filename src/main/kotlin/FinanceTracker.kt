import java.util.*

interface FinanceTracker {

    fun add(transaction: Transaction)
    fun viewAllTransactions(): List<Transaction>
    fun editTransaction(
    transaction:Transaction
    ): Result

    fun deleteTransaction(transactionId: Int)
    fun getSummary(): Summary
}