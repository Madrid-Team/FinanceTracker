object FinanceTrackerImpl : FinanceTracker {

    private val _transactions: MutableList<Transaction> = mutableListOf()
    val transactions = _transactions.toList()

    override fun add(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun viewAllTransactions(transactions: List<Transaction>):String {
        if (transactions.isEmpty()) return "No transactions found."
        else{
            var str = ""
            for (trasaction in transactions){
                str += " ID: ${trasaction.id}\n"
                str += " Type: ${trasaction.type}\n"
                str += " Amount: ${trasaction.amount}\n"
                str += " Category: ${trasaction.category.name}\n"
                str += " Date: ${trasaction.date}\n"

            }
            return str.trim()
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