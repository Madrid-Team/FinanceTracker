import common.isValidCategory

object FinanceTrackerImpl : FinanceTracker {

    private val _transactions: MutableList<Transaction> = mutableListOf()


    fun getTransactionById(transactionId: Int): Transaction? {
        return _transactions.find { it.id == transactionId }
    }

    override fun add(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun viewAllTransactions(): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun editTransaction(
        transaction: Transaction,
    ): Result<Unit> {
        return when {
            transaction.amount <= 0 -> Result.Error(cause = "Amount must be greater than 0")
            !transaction.category.name.isValidCategory() -> Result.Error(cause = "please provide a valid category name with more than 3 characters and no special characters")
            else -> {
                val transactionIndex = _transactions.indexOfFirst { it.id == transaction.id }
                return if (transactionIndex != -1) {
                    _transactions[transactionIndex] = transaction
                    Result.Success(Unit)
                } else {
                    Result.Error("Transaction not found")
                }
            }
        }
    }

    override fun deleteTransaction(transactionId: Int) {
        TODO("Not yet implemented")
    }

    override fun getSummary(): Summary {
        TODO("Not yet implemented")
    }

}