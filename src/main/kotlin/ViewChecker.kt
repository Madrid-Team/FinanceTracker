import FinanceTrackerImpl.viewAllTransactions
import java.util.*

fun main(){
    val categoryFood = Category("Food")
    val categorySalary = Category("Salary")

    val transaction1 = Transaction(
        id = 1,
        type = TransactionType.EXPENSES,
        amount = 50.0,
        category = categoryFood,
        date = Date(2025 - 1900, 3, 5) // April 5, 2025
    )

    val transaction2 = Transaction(
        id = 2,
        type = TransactionType.INCOME,
        amount = 1000.0,
        category = categorySalary,
        date = Date(2025 - 1900, 3, 1)
    )

    val transactions = listOf(transaction1, transaction2)
    checkViewAllTransactions(name = "n1", result = viewAllTransactions(transactions), correctResult = "c1")
}



fun checkViewAllTransactions(name: String, result: String, correctResult: String) {
    if (result == correctResult) {
        println("Success -> $name")
    } else {
        println("Failed->$name \nExpected:$correctResult \nGot:$result")
    }
}