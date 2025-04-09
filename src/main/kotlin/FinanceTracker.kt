import java.util.*

interface FinanceTracker {

    fun add(transaction: Transaction)
    fun viewAllTransactions(): List<Transaction>
    fun editTransaction(
        transactionId: Int,
        newType: TransactionType? = null,
        newAmount: Double? = null,
        newCategory: Category? = null,
        newDate: Date? = null
    ): Boolean

    fun deleteTransaction(transactionId: Int)
    fun getSummary(): Summary
}