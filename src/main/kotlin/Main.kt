import java.util.*

fun main() {

    view()
}
private fun view() {
//    val tracker = FinanceTrackerImpl
//    val transaction1 = Transaction(
//        id = 1,
//        type = TransactionType.EXPENSES,
//        amount = 50.0,
//        category = Category("Food"),
//        date = Date(2025 - 1900, 3, 5) // April 5, 2025
//    )
//
//    val transaction2 = Transaction(
//        id = 2,
//        type = TransactionType.INCOME,
//        amount = 1000.0,
//        category = Category("Salary"),
//        date = Date(2025 - 1900, 3, 1)
//    )
//    val transactions = listOf(transaction1)
//    val listOfTwoTransactions = listOf(transaction1,transaction2)
    while (true) {
        println(
            """
            --------------------------
            Enter 1 to view all transactions
            Enter 2 to view monthly summary
            Enter 3 to view most used category
            Enter 4 to go back
            --------------------------
        """.trimIndent()
        )

        print("Selected option: ")

        val option = try {
            readln().toInt()
        } catch (e: Exception) {
            println("Please enter a valid number.")
            continue
        }

        when (option) {
            1 -> {
                val transactions = FinanceTrackerImpl.transactions
                val result = FinanceTrackerImpl.viewAllTransactions(transactions)
                println(result)
            }

            2 -> {
                println("Enter year:")
                val year = readln().toIntOrNull()
                println("Enter month (1 to 12) or leave empty for full year:")
                val monthInput = readln()
                val month = if (monthInput.isNotBlank()) monthInput.toIntOrNull() else null

                if (year != null) {
                    val summary = FinanceTrackerImpl.getMonthlySummary(month?. minus(1), year)
                    println("Income: ${summary.income}")
                    println("Expenses: ${summary.expenses}")
                    println("Remaining: ${summary.remaining}")
                } else {
                    println("Invalid year.")
                }
            }

            3 -> {
                println("Enter year:")
                val year = readln().toIntOrNull()
                println("Enter month (1 to 12) or leave empty for full year:")
                val monthInput = readln()
                val month = if (monthInput.isNotBlank()) monthInput.toIntOrNull() else null

                val transactions = FinanceTrackerImpl.transactions
                if (year != null) {
                    val result = FinanceTrackerImpl.viewMostcategory(month?. minus(1), year, transactions)
                    println(result)
                } else {
                    println("Invalid year.")
                }
            }

            4 -> break

            else -> println("Please choose from 1 to 4 only.")
        }
    }
}