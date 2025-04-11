import FinanceTrackerImpl.loadTransactions
import java.time.LocalDate

fun main() {
    val tracker = FinanceTrackerImpl

    val transaction1 = listOf(
        Transaction(1, TransactionType.INCOME, 1500.0, Category("Freelance"), LocalDate.now()),
        Transaction(2, TransactionType.INCOME, 200.0, Category("Groceries"), LocalDate.now())
    )

    val transaction2 = Transaction(7, TransactionType.INCOME, 1500.0, Category("Freelance"), LocalDate.now())

    tracker.saveTransactions(transaction2)

    val loaded = loadTransactions()
    loaded.forEach {
        println("- ${it.id} : ${it.type} .. ${it.amount} .. ${it.category} .. ${it.date}")
    }
}