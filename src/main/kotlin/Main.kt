import FinanceTrackerImpl.loadTransactions
import common.isValidCategory
import common.parseDate
import java.lang.Exception
import java.time.LocalDate

import java.util.*

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


private fun edit() {

    var category: String? = null
    var amount: Double? = null
    var type: TransactionType? = null
    var date: LocalDate? = null
    var option: Int? = null
    var transaction: Transaction? = null

    do {
        println("Please enter transaction id:")
        val input = readln()
        try {
            val id = input.toInt()
            transaction = FinanceTrackerImpl.getTransactionById(id)
            if (transaction == null) {
                println("Id not exist")
            }
        } catch (e: Exception) {
            println("id must be number")
        }
    } while (transaction == null)


    while (true) {
        println(
            """
                
            Enter 1 for edit the amount
            Enter 2 for edit the category
            Enter 3 for edit the type
            Enter 4 for edit the date
            Enter 5 for exit
            
        """.trimIndent()
        )
        println("selected option:")
        try {
            option = readln().toInt()
        } catch (e: Exception) {
            continue
        }

        when (option) {
            1 -> {
                do {
                    println("Please enter new amount:")
                    try {
                        amount = readln().toDouble()

                        transaction = transaction?.copy(
                            amount = amount
                        )
                    } catch (e: Exception) {
                        println("amount must be number")
                    }
                } while (amount == null)
            }

            2 -> {
                do {
                    println("Please enter new category:")
                    try {
                        val input = readln()
                        category = if (input.isValidCategory()) input else null
                        transaction = transaction?.copy(
                            category = Category(name = category!!)
                        )
                    } catch (e: Exception) {
                        println("amount must be valid string")
                    }
                } while (category == null)
            }

            3 -> {
                do {
                    println(
                        """
                            
                            Please enter new type:
                            0 for INCOME
                            1 for EXPENSES
                            
                        """.trimIndent()
                    )
                    try {
                        val input = readln().toInt()
                        when (input) {
                            0 -> {
                                type = TransactionType.INCOME
                                transaction = transaction?.copy(
                                    type = type
                                )
                            }

                            1 -> {
                                type = TransactionType.EXPENSES
                                transaction = transaction?.copy(
                                    type = type
                                )
                            }

                            else -> {
                                println("please enter only 0 or 1")
                            }
                        }
                    } catch (e: Exception) {
                        println("please enter only 0 or 1")
                    }
                } while (type == null)
            }

            4 -> {

                do {
                    println("Please enter valid date (yy-mm-dd):")
                    try {
                        val input = readln()
                        date = parseDate(input)
                        transaction = transaction?.copy(
                            date = date
                        )
                    } catch (e: Exception) {
                        println("please enter valid date with this format yy-mm-dd")
                    }
                } while (date == null)
            }

            5 -> {
                transaction?.let {
                    FinanceTrackerImpl.editTransaction(transaction)
                }
                break
            }

        }
    }

}

private fun deleteTransaction() {
    println("Please enter transaction with id: ")

    lable1@ do {
        try {
            val idTransaction = readln().toInt()
            val transactionDeleted = FinanceTrackerImpl.deleteTransaction(idTransaction)
            if (transactionDeleted) {
                println("Transaction deleted is successful")
                break@lable1
            } else {
                println("Transaction not found do you want to continue?(y/n)")
                do {
                    try {
                        val option = readln()
                        if (option == "n") {
                            break@lable1
                        } else if (option == "y") {
                            println("Done")
                            break
                        }
                    }catch (e:Exception){
                        println("Please enter valid option: (y/n)")
                    }
                }while (true)

            }
        } catch (e: Exception) {
            println("Please enter valid transaction id: ")
        }
    } while (true)


}