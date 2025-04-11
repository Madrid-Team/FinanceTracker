import java.util.Date

fun main() {

    val category = Category(name = "Food")
    val transaction1 = Transaction(id = 1, TransactionType.INCOME, amount = 500.0, category = category, date = Date())
    val transaction2 = Transaction(id = 2, TransactionType.EXPENSES, amount = 600.0, category = category, date = Date())
    val transaction3 = Transaction(id = 3, TransactionType.INCOME, amount = 700.0, category = category, date = Date())



    val financeTracker = FinanceTrackerImpl(logger = FileCache())
    financeTracker.add(transaction1)
    financeTracker.add(transaction2)
    financeTracker.add(transaction3)
    financeTracker.deleteTransaction(2)


}