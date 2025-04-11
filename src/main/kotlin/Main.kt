import FinanceTrackerImpl.loadTransactions

fun main() {
    val tracker = FinanceTrackerImpl

    val transaction1 = listOf(
        Transaction(1, TransactionType.INCOME, 1500.0, Category("Freelance"), "2025-04-10"),
        Transaction(2, TransactionType.INCOME, 200.0, Category("Groceries"), "2025-04-11")
    )

    val transaction2 = listOf(
        Transaction(4, TransactionType.INCOME, 1500.0, Category("Freelance"), "2025-04-10")
    )

    val transaction3 = listOf(
        Transaction(2, TransactionType.EXPENSES, 100.0, Category("Food"), "2024-03-20")
    )
    tracker.saveTransactions(transaction3)

    val loaded = loadTransactions()
    loaded.forEach {
        println("- ${it.id} : ${it.type} .. ${it.amount} .. ${it.category} .. ${it.date}")
    }
}