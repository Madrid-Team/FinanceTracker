object FinanceTrackerImpl : FinanceTracker {

    private val _transactions: MutableList<Transaction> = mutableListOf()
    val transactions = _transactions.toList()

    override fun add(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun viewAllTransactions(): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun editTransaction(transactionId: Int) {
        TODO("Not yet implemented")
    }

    override fun deleteTransaction(transactionId: Int):Boolean {
        val transactionToRemove = _transactions.find { it.id == transactionId } ?: return false
        return _transactions.remove(transactionToRemove)
    }

    override fun getSummary(): Summary {
        TODO("Not yet implemented")
    }

}