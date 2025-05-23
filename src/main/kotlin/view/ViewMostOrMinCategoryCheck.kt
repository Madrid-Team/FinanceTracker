package view

import Category
import FinanceTrackerImpl.viewMinCategory
import FinanceTrackerImpl.viewMostCategory
import Transaction
import TransactionType
import java.time.LocalDate

fun main() {
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
        id = 3,
        type = TransactionType.INCOME,
        amount = 1000.0,
        category = categorySalary2,
        date = LocalDate.of(2025, 4, 5) // April 5, 2025
    )
    val transaction4 = Transaction(
        id = 4,
        type = TransactionType.INCOME,
        amount = 1000.0,
        category = categorySalary3,
        date = LocalDate.of(2025, 4, 5) // April 5, 2025
    )
    val transaction5 = Transaction(
        id = 5,
        type = TransactionType.INCOME,
        amount = 1000.0,
        category = categorySalary4,
        date = LocalDate.of(2025, 4, 5) // April 5, 2025
    )
    val transactions = listOf<Transaction>()
    val onItemTransactions = listOf(transaction1)

    val listOfTransactions = listOf(transaction1, transaction2, transaction3, transaction4, transaction5)
    checkViewMostOrMinCategory(
        "correct case in viewMostCategory",
        viewMostCategory(3, 2025 - 1900, listOfTransactions),
        "most category is  salary appears 3"
    )
    checkViewMostOrMinCategory(
        "empty Transaction List ",
        viewMostCategory(3, 2025 - 1900, transactions),
        "The List is empty "
    )
    checkViewMostOrMinCategory(
        "One Item in List in viewMostCategory ",
        viewMostCategory(3, 2025 - 1900, onItemTransactions),
        "most category is  Food appears 1"
    )
    checkViewMostOrMinCategory(
        "correct case in viewMinCategory ",
        viewMinCategory(3, 2025 - 1900, listOfTransactions),
        "most category is  Food appears 2"
    )
    checkViewMostOrMinCategory(
        "empty Transaction List  ",
        viewMinCategory(3, 2025 - 1900, transactions),
        "The List is empty "
    )
    checkViewMostOrMinCategory(
        "One Item in List  in viewMinCategory",
        viewMinCategory(3, 2025 - 1900, onItemTransactions),
        "most category is  Food appears 1"
    )

}

fun checkViewMostOrMinCategory(caseName: String, result: String, correctResult: String) {
    if (result == correctResult) {
        println("$caseName   is Success")
    } else {
        println("$caseName  is Failed")
    }
}