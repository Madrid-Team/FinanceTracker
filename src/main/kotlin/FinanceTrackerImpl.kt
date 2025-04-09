object FinanceTrackerImpl : FinanceTracker {

    private val _transactions: MutableList<Transaction> = mutableListOf()
    val transactions = _transactions.toList()

    override fun add(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun viewAllTransactions(transactions: List<Transaction>):String {
        if (transactions.isEmpty()) return "No transactions found."
        else{
            return " "
        }
    }

    override fun editTransaction(transactionId: Int) {
        TODO("Not yet implemented")
    }

    override fun deleteTransaction(transactionId: Int) {
        TODO("Not yet implemented")
    }

    override fun getMonthSummary(month: Int, year: Int): Summary {
        return Summary(income = 500.0, expenses = 300.0, remaining = 200.0)
    }

}