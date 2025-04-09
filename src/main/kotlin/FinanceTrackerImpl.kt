object FinanceTrackerImpl : FinanceTracker {

    private val _transactions: MutableList<Transaction> = mutableListOf()
    val transactions = _transactions.toList()

    override fun add(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun viewAllTransactions(transactions: List<Transaction>): String {
        if (transactions.isEmpty()) return "No transactions found."
        else {
            var str = ""
            for (trasaction in transactions) {
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

    override fun getMonthlySummary(month: Int?, year: Int): Summary {
    if(!transactions.isNullOrEmpty()) {
        val monthTransaction =
            _transactions.filter { if (month != null && month in 0..11) it.date.month == month && it.date.year == year else it.date.year == year }
        val totalIncome = monthTransaction.filter { it.type == TransactionType.INCOME }.sumOf { it.amount }
        val totalExpenses = monthTransaction.filter { it.type == TransactionType.EXPENSES }.sumOf { it.amount }
        val remaining = totalIncome - totalExpenses
        return Summary(income = totalIncome, expenses = totalExpenses, remaining = remaining)
    }
        else{
            return  Summary(income = 0.0, expenses = 0.0, remaining = 0.0)
        }
    }

}