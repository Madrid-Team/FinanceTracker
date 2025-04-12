package edit

import Category
import FinanceTrackerImpl
import Transaction
import TransactionType
import java.time.LocalDate

fun main() {
    val transaction1 = Transaction(1, TransactionType.INCOME, 500.0, Category("Salary"), LocalDate.now())
    val transaction2 = Transaction(2, TransactionType.EXPENSES, 100.0, Category("Food"), LocalDate.now())

    check(
        name = "Edit existing transaction should return true",
        result = FinanceTrackerImpl.editTransaction(transaction1.copy(amount = 600.0)),
        correctResult = "edit operation succeeded"
    )

    check(
        name = "Edit with invalid amount should return Error",
        result = FinanceTrackerImpl.editTransaction(transaction2.copy(amount = -100.0)),
        correctResult = "Amount must be greater than 0"
    )


    check(
        name = "Edit new category with valid category name should return true",
        result = FinanceTrackerImpl.editTransaction(transaction1.copy(category = Category("Food"))),
        correctResult = "edit operation succeeded"
    )

    check(
        name = "Edit with invalid category name should return Error",
        result = FinanceTrackerImpl.editTransaction(transaction1.copy(category = Category("12@"))),
        correctResult = "please provide a valid category name with more than 3 characters and no special characters"
    )

    check(
        name = "Edit transaction with empty category name should return Error",
        result = FinanceTrackerImpl.editTransaction(transaction1.copy(category = Category(""))),
        correctResult = "please provide a valid category name with more than 3 characters and no special characters"
    )

   check(
       name ="Edit date in the Future should return Error",
       result = FinanceTrackerImpl.editTransaction(transaction1.copy(date = LocalDate.now().plusDays(1))),
       correctResult = "Date cannot be in the Future",
   )
    check(
        name = "Edit transaction date in acceptable past should return true",
        result = FinanceTrackerImpl.editTransaction(transaction1.copy(date = LocalDate.now().minusDays(1))),
        correctResult = "edit operation succeeded"
        )

    check(
        name = "Edit transaction Date in way past should return Error",
        result = FinanceTrackerImpl.editTransaction(transaction1.copy(date = LocalDate.now().minusYears(100))),
        correctResult = "Date is too far in the past"
    )


}

fun check(name: String, result: String, correctResult: String) {
    val passed = result == correctResult

    if (passed) {
        println("Success : $name")
    } else {
        println("Failed : $name")
        println("Error : $result")
    }
}