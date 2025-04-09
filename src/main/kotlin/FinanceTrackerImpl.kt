import java.util.*

object FinanceTrackerImpl : FinanceTracker {

    private val _transactions: MutableList<Transaction> = mutableListOf()
    val transactions = _transactions.toMutableList()

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
        val index = transactions.indexOfFirst { it.id == transactionId }
        if (index == -1) return false
        val currentTransaction = transactions[index]
        if (currentTransaction.amount < 0 || currentTransaction.category.name.any { !it.isLetter() }) return false
        val updatedTransaction = currentTransaction.copy(
            type = newType ?: currentTransaction.type,
            amount = newAmount ?: currentTransaction.amount,
            category = newCategory ?: currentTransaction.category,
            date = newDate ?: currentTransaction.date
        )
        if (updatedTransaction == currentTransaction) return false
        transactions[index] = updatedTransaction
        return true
    }

    override fun deleteTransaction(transactionId: Int) {
        TODO("Not yet implemented")
    }

    override fun getSummary(): Summary {
        TODO("Not yet implemented")
    }

}