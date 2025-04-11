import FinanceTrackerImpl.loadTransactions
import java.time.LocalDate

fun main() {
    val tracker = FinanceTrackerImpl
    val transaction = Transaction(
        7,
        TransactionType.INCOME, 1500.0,
        Category("Freelance"),
        LocalDate.now()
    )
    tracker.saveTransactions(transaction)

    val loaded = loadTransactions()
    loaded.forEach {
        println("- ${it.id} : ${it.type} .. ${it.amount} .. ${it.category} .. ${it.date}")
    }
}