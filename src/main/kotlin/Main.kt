import FinanceTrackerImpl.loadTransactions
import java.time.LocalDate

fun main() {
    val tracker = FinanceTrackerImpl

    val transaction1 = listOf(
        Transaction(1, TransactionType.INCOME, 1500.0, Category("Freelance"), LocalDate.now()),
        Transaction(2, TransactionType.INCOME, 200.0, Category("Groceries"), LocalDate.now())
    )

    val transaction2 = listOf(
        Transaction(4, TransactionType.INCOME, 1500.0, Category("Freelance"), LocalDate.now())
    )

    val transaction3 = listOf(
        Transaction(2, TransactionType.EXPENSES, 100.0, Category("Food"), LocalDate.now())
    )
    tracker.saveTransactions(transaction3)

    val loaded = loadTransactions()
    loaded.forEach {
        println("- ${it.id} : ${it.type} .. ${it.amount} .. ${it.category} .. ${it.date}")
    }
}