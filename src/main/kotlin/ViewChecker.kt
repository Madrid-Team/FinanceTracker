import FinanceTrackerImpl.viewAllTransactions
import java.time.LocalDate
import java.util.*


fun main() {
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
    val transactions = listOf(transaction1)
    val listOfTwoTransactions = listOf(transaction1,transaction2)
    val expected = """
   ID: 1
 Type: EXPENSES
 Amount: 50.0
 Category: Food
 Date: ${transaction1.date}
 ID: 2
 Type: INCOME
 Amount: 1000.0
 Category: Salary
 Date: ${transaction2.date}
""".trim()
    checkViewAllTransactions(
        name = "Test viewAllTransactions with two items",
        result = viewAllTransactions(listOfTwoTransactions), correctResult = expected
    )
    checkViewAllTransactions(
        name = "Test viewAllTransactions with one item",
        result = viewAllTransactions(transactions), correctResult = expected
    )
    val emptyTransactionsList = emptyList<Transaction>()
    checkViewAllTransactions(
        name = "Test viewAllTransactions with empty transactions list",
        result = viewAllTransactions(emptyTransactionsList),
        correctResult = "No transactions found."
    )
}


fun checkViewAllTransactions(name: String, result: String, correctResult: String) {
    if (result == correctResult) {
        println("Success -> $name \nExpected:\n$correctResult \nGot:\n$result")
        println("_________________________________________________________________")
    } else {
        println("Failed->$name \nExpected:\n$correctResult \nGot:\n$result")
        println("_________________________________________________________________")
    }
}