import FinanceTrackerImpl.loadTransactions

fun main() {
    val tracker = FinanceTrackerImpl

    val transactions = listOf(
        Transaction(1, TransactionType.INCOME, 1500.0, Category("Freelance"), "2025-04-10"),
        Transaction(2, TransactionType.INCOME, 200.0, Category("Groceries"), "2025-04-11")
    )

    tracker.saveTransactions(transactions)

    val loaded = loadTransactions()
    loaded.forEach {
        println("- ${it.id} : ${it.type} .. ${it.amount} .. ${it.category} .. ${it.date}")
    }
}