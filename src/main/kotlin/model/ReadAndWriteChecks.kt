package model

import Category
import FinanceTrackerImpl.viewMinCategory
import FinanceTrackerImpl.viewMostcategory
import Transaction
import java.time.LocalDate

fun main() {
    //println(loadTransactionsFromFile(filePath = ReadAndWriteFile.filePath).toString())
    val categoryFood = Category("Food")
    val categorySalary = Category("salary")
    val categorySalary2 = Category("salary")
    val categorySalary3 = Category("salary")
    val categorySalary4 = Category("Food")


    val transaction1 = Transaction(
        id = 1,
        type = TransactionType.EXPENSES,
        amount = 50.0,
        category = categoryFood,
        date = LocalDate.of(2025, 4, 5) // April 5, 2025
    )

    val transaction2 = Transaction(
        id = 2,
        type = TransactionType.INCOME,
        amount = 1000.0,
        category = categorySalary,
        date = LocalDate.of(2025, 4, 5) // April 5, 2025
    )
    val transaction3 = Transaction(
        id = 2,
        type = TransactionType.INCOME,
        amount = 1000.0,
        category = categorySalary2,
        date = LocalDate.of(2025, 4, 5) // April 5, 2025
    )
    val transaction4 = Transaction(
        id = 2,
        type = TransactionType.INCOME,
        amount = 1000.0,
        category = categorySalary3,
        date = LocalDate.of(2025, 4, 5) // April 5, 2025
    )
    val transaction5 = Transaction(
        id = 2,
        type = TransactionType.INCOME,
        amount = 1000.0,
        category = categorySalary4,
        date = LocalDate.of(2025, 4, 5) // April 5, 2025
    )
    val transactions = listOf<Transaction>()
    val transactionsList = listOf<Transaction>(transaction1,transaction2)

    val listOfTransactions = listOf(transaction1,transaction2,transaction3,transaction4 ,transaction5)
    CheckReadAndWrite("correct case in Read",loadTransactionsFromFile(filePath = ReadAndWriteFile.filePath).toString(),"[Transaction(id=1, type=INCOME, amount=1500.0, category=Category(name=Salary), date=2025-08-05), Transaction(id=2, type=INCOME, amount=10500.0, category=Category(name=Salary2), date=2025-09-05)]")


}

    fun CheckReadAndWrite(caseName: String, result: String, correctResult: String) {
        if (result == correctResult) {
            println("${caseName}   is Success")
        } else {
            println("${caseName}  is Failed")

        }

    }
