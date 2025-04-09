import java.time.LocalDate

object FinanceTrackerImpl : FinanceTracker {

    private val _transactions: MutableList<Transaction> = mutableListOf()
    val transactions = _transactions.toList()

    override fun add(transaction: Transaction): Boolean {
        if (transaction.amount <= 0) return false
        if (transaction.category.name.isBlank()) return false
        if (transaction.date.isAfter(LocalDate.now())) return false

        val nextId = if (_transactions.isEmpty()) 1 else _transactions.maxOf { it.id } + 1
        val newTransaction = transaction.copy(id = nextId)
        _transactions.add(newTransaction)
        return true

    }

    override fun viewAllTransactions(): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun editTransaction(transactionId: Int) {
        TODO("Not yet implemented")
    }

    override fun deleteTransaction(transactionId: Int) {
        TODO("Not yet implemented")
    }

    override fun getSummary(): Summary {
        TODO("Not yet implemented")
    }

}