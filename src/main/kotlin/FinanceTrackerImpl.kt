import common.isValidCategory
import java.util.*

object FinanceTrackerImpl : FinanceTracker {

    private val _transactions: MutableList<Transaction> = mutableListOf()

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

        if (newAmount == null && newType == null && newDate == null && newCategory == null) return false

        if (newAmount != null && newAmount <= 0 ) return false

        if (newCategory != null && !newCategory.name.isValidCategory()) return false

        val transaction = _transactions.find { it.id == transactionId } ?: return false

        val transactionIndex = _transactions.indexOf(transaction)

        val updatedTransaction = transaction.copy(
            type = newType ?: transaction.type,
            amount = newAmount ?: transaction.amount,
            category = newCategory ?: transaction.category,
            date = newDate ?: transaction.date
        )

        _transactions[transactionIndex] = updatedTransaction
        return true
    }

    override fun deleteTransaction(transactionId: Int) {
        TODO("Not yet implemented")
    }

    override fun getSummary(): Summary {
        TODO("Not yet implemented")
    }

}