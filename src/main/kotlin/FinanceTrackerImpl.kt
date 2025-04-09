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

    override fun deleteTransaction(transactionId: Int) {
        // get the transaction
        val transaction = transactions.find { transaction -> transaction.id == transactionId }
        if (transaction == null) {
            println("Transaction not found")
            return
        }

        // Remove the transaction
        _transactions.remove(transaction)

        println("Transaction $transactionId has been deleted")
    }

    override fun getSummary(): Summary {
        TODO("Not yet implemented")
    }

}