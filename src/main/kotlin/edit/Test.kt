package edit

import Category
import Result
import Transaction
import java.util.*

fun main() {
    val transaction1 = Transaction(1, TransactionType.INCOME, 500.0, Category("Salary"), Date())
    val transaction2 = Transaction(2, TransactionType.EXPENSES, 100.0, Category("Food"), Date())

    check(
        name = "Edit existing transaction should return true",
        result = FinanceTrackerImpl.editTransaction(transaction1.copy(amount = 500.0)),
        correctResult = Result.Success(Unit)
    )

    check(
        name = "Edit with invalid amount should return Error",
        result = FinanceTrackerImpl.editTransaction(transaction2.copy(amount = -100.0)),
        correctResult = Result.Error("Amount must be greater than 0")
    )


    check(
        name = "Edit new category with valid category name should return true",
        result = FinanceTrackerImpl.editTransaction(transaction1.copy(category = Category("Food"))),
        correctResult = Result.Success(Unit)
    )

    check(
        name = "Edit with invalid category name should return Error",
        result = FinanceTrackerImpl.editTransaction(transaction1.copy(category = Category("12@"))),
        correctResult = Result.Error("please provide a valid category name with more than 3 characters and no special characters")
    )
}

fun <T> check(name: String, result: Result<T>, correctResult: Result<Unit>) {
    val passed = when {
        result is Result.Success && correctResult is Result.Success -> result.data == correctResult.data
        result is Result.Error && correctResult is Result.Error -> result.cause == correctResult.cause
        else -> false
    }

    if (passed) {
        println("Success : $name")
    } else {
        println("Failed : $name")
        println("Expected : $correctResult")
        println("Got : $result")
    }
}