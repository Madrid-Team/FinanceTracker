import FinanceTrackerImpl.loadTransactions
import common.isValidCategory
import common.parseDate
import java.time.LocalDate

fun main() {
    while (true) {
        println(
            """
            --------------------------
            Finance Tracker
            --------------------------
            1 - Manage Transactions
            2 - View Summary
            3 - Exit
            --------------------------
        """.trimIndent()
        )

        print("Select an option: ")
        when (readln().toIntOrNull()) {
            1 -> transactionHandler()
            2 -> view()
            3 -> {
                println("Goodbye!")
                return
            }

            else -> println("Invalid input. Please choose between 1 and 3.")
        }
    }
}

private fun transactionHandler() {
    while (true) {
        println(
            """
            --------------------------
            Transaction Management
            --------------------------
            1 - View all transactions
            2 - Add transaction
            3 - Edit transaction
            4 - Delete transaction
            5 - Go back
            --------------------------
        """.trimIndent()
        )

        print("Select an option: ")
        when (readln().toIntOrNull()) {
            1 -> loadTransactions().forEach {
                println("- ${it.id} : ${it.type} .. ${it.amount} .. ${it.category} .. ${it.date}")
            }

            2 -> add()
            3 -> edit()
            4 -> deleteTransaction()
            5 -> return
            else -> println("Invalid option. Please choose from 1 to 5.")
        }
    }
}

private fun view() {
    while (true) {
        println(
            """
            --------------------------
            View Options
            --------------------------
            1 - View all transactions
            2 - Monthly summary
            3 - Most used category
            4 - Least used category
            5 - Go back
            --------------------------
        """.trimIndent()
        )

        print("Select an option: ")
        when (val option = readln().toIntOrNull()) {
            1 -> println(FinanceTrackerImpl.viewAllTransactions(FinanceTrackerImpl.transactions))

            2, 3, 4 -> {
                print("Enter year: ")
                val year = readln().toIntOrNull()

                print("Enter month (1-12) or leave blank for full year: ")
                val month = readln().takeIf { it.isNotBlank() }?.toIntOrNull()?.minus(1)

                if (year != null) {
                    when (option) {
                        2 -> {
                            val summary = FinanceTrackerImpl.getMonthlySummary(month, year)
                            println("Income: ${summary.income}, Expenses: ${summary.expenses}, Remaining: ${summary.remaining}")
                        }

                        3 -> {
                            val transactions = FinanceTrackerImpl.transactions
                            println(FinanceTrackerImpl.viewMostCategory(month, year, transactions))
                        }

                        4 -> {
                            val transactions = FinanceTrackerImpl.transactions
                            println(FinanceTrackerImpl.viewMinCategory(month, year, transactions))
                        }
                    }
                } else println("Invalid year.")
            }

            4 -> return
            else -> println("Invalid option. Choose between 1 and 4.")
        }
    }
}

private fun add() {
    print("Enter transaction amount: ")
    val amount = readln().toDoubleOrNull()
    if (amount == null || amount <= 0) {
        println("Amount must be greater than 0.")
        return
    }

    println("Enter transaction type:\n1 - Income\n2 - Expenses")
    val type = when (readln().toIntOrNull()) {
        1 -> TransactionType.INCOME
        2 -> TransactionType.EXPENSES
        else -> {
            println("Invalid type.")
            return
        }
    }

    print("Enter transaction category: ")
    val categoryInput = readln()
    if (!categoryInput.isValidCategory()) {
        println("Invalid category.")
        return
    }

    val id = FinanceTrackerImpl.transactions.size + 1
    val transaction = Transaction(id, type, amount, Category(categoryInput), LocalDate.now())
    FinanceTrackerImpl.saveTransactions(transaction)

    println("Transaction added.")
}

private fun edit() {
    print("Enter transaction ID: ")
    val transaction = readln().toIntOrNull()?.let { FinanceTrackerImpl.getTransactionById(it) }

    if (transaction == null) {
        println("Transaction not found.")
        return
    }

    var updatedTransaction = transaction

    while (true) {
        println(
            """
            --------------------------
            Edit Transaction
            --------------------------
            1 - Edit amount
            2 - Edit category
            3 - Edit type
            4 - Edit date
            5 - Save and exit
        """.trimIndent()
        )

        print("Select an option: ")
        when (readln().toIntOrNull()) {
            1 -> {
                print("Enter new amount: ")
                val newAmount = readln().toDoubleOrNull()
                if (newAmount != null && newAmount > 0 && updatedTransaction != null) {
                    updatedTransaction = updatedTransaction.copy(amount = newAmount)
                } else println("Invalid amount.")
            }

            2 -> {
                print("Enter new category: ")
                val newCategory = readln()
                if (newCategory.isValidCategory() && updatedTransaction != null) {
                    updatedTransaction = updatedTransaction.copy(category = Category(newCategory))
                } else println("Invalid category.")
            }

            3 -> {
                println("Enter new type: 1 - Income, 2 - Expenses")
                if (updatedTransaction != null) {
                    updatedTransaction = when (readln().toIntOrNull()) {
                        1 -> updatedTransaction.copy(type = TransactionType.INCOME)
                        2 -> updatedTransaction.copy(type = TransactionType.EXPENSES)
                        else -> {
                            println("Invalid type.")
                            updatedTransaction
                        }
                    }
                }
            }

            4 -> {
                print("Enter new date (yyyy-mm-dd): ")
                val date = try {
                    parseDate(readln())
                } catch (e: Exception) {
                    println("Invalid date format.")
                    null
                }
                if (date != null) if (updatedTransaction != null) {
                    updatedTransaction = updatedTransaction.copy(date = date)
                }
            }

            5 -> {
                if (updatedTransaction != null) {
                    FinanceTrackerImpl.editTransaction(updatedTransaction)
                }
                println("Transaction updated.")
                return
            }

            else -> println("Invalid option.")
        }
    }
}

private fun deleteTransaction() {
    while (true) {
        print("Enter transaction ID to delete: ")
        val id = readln().toIntOrNull()

        if (id == null) {
            println("Invalid ID.")
            continue
        }

        val result = FinanceTrackerImpl.deleteTransaction(id)
        if (result) {
            println("Transaction deleted.")
            return
        } else {
            println("Transaction not found. Try again? (y/n)")
            when (readln().lowercase()) {
                "n" -> return
                "y" -> continue
                else -> println("Invalid choice.")
            }
        }
    }
}