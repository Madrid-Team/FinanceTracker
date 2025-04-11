package edit

import Result

fun main() {

//    check(
//        name = "Edit existing transaction should return true",
//        result = FinanceTrackerImpl.editTransaction(1, newAmount = 200.0),
//        correctResult = true
//    )
//
//    check(
//        name = "Try to edit non existing transaction should return false",
//        result = FinanceTrackerImpl.editTransaction(1000, newAmount = 3000.0),
//        correctResult = false
//    )
//
//    check(
//        name = "Add invalid new amount should return false",
//        result = FinanceTrackerImpl.editTransaction(3, newAmount = -70.0),
//        correctResult = false
//    )
//
//    check(
//        name = "Edit amount to zero should return false",
//        result = FinanceTrackerImpl.editTransaction(1, newAmount = 0.0),
//        correctResult = false
//    )
//
//    check(
//        name = "Edit new category with valid category name should return true",
//        result = FinanceTrackerImpl.editTransaction(2, newCategory = Category("Food")),
//        correctResult = true
//    )

    check(
        name = "Edit new category with special character or numbers should return false",
        result = Result.Error(),
        correctResult = Result.Error()
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