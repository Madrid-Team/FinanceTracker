package edit

import Category
import FinanceTrackerImpl

fun main() {
    val tracker = FinanceTrackerImpl
    check(
        name = "Edit existing transaction should return true",
        result = tracker.editTransaction(1, newAmount = 200.0),
        correctResult = true
    )

    check(
        name = "Try to edit non existing transaction should return false",
        result = tracker.editTransaction(1000, newAmount = 3000.0),
        correctResult = false
    )

    check(
        name = "Add invalid new amount should return false",
        result = tracker.editTransaction(3, newAmount = -70.0),
        correctResult = false
    )

    check(
        name = "Edit amount to zero should return false",
        result = tracker.editTransaction(1, newAmount = 0.0),
        correctResult = false
    )

    check(
        name = "Edit with no changes should return false",
        result = tracker.editTransaction(1),
        correctResult = false
    )

    check(
        name = "Edit new category with special character or numbers should return false",
        result = tracker.editTransaction(2, newCategory = Category("Food123#")),
        correctResult = false
    )
}

fun check(name: String, result: Boolean, correctResult: Boolean) {
    if (result == correctResult) {
        println("Success - $name")
    } else {
        println("Failed - $name")
    }
}