package deleteTestCases

import FinanceTrackerImpl


fun main(){
    check(
        name = "Delete an existing transaction should return true",
        result = FinanceTrackerImpl.deleteTransaction(1),
        correctResult = true
    )
    check(
       name =  "Delete non-existing transaction should return false",
        result = FinanceTrackerImpl.deleteTransaction(transactionId = 1000),
        correctResult = false
    )

    check(
        name = "Delete from empty transaction list should return false",
        result = FinanceTrackerImpl.deleteTransaction(2),
        correctResult = false
    )

    check(
        name = "Delete with ID negative should return false",
        result = FinanceTrackerImpl.deleteTransaction(-4),
        correctResult = false
    )

}

fun check(name: String, result: Boolean, correctResult: Boolean) {
    if (result == correctResult)
        println("Success - $name")
    else
        println("Failed - $name")
}