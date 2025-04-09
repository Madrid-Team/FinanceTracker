import java.util.*

object FinanceTrackerImpl : FinanceTracker {

    private val _transactions: MutableList<Transaction> = mutableListOf()
    val transactions = _transactions.toList()

    override fun add(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun viewAllTransactions(): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun editTransaction(
        transactionId: Int,
        newType: TransactionType?,
        newAmount: Double?,
        newCategory: Category?,
        newDate: Date?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteTransaction(transactionId: Int) {
        TODO("Not yet implemented")
    }

    override fun getSummary(): Summary {
        TODO("Not yet implemented")
    }

}