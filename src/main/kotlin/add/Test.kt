package add

import Category
import FinanceTrackerImpl
import Transaction
import java.time.LocalDate

fun main() {
    val financeTrackerImp = FinanceTrackerImpl

//    checkAddTransaction(
//        name = "when given a valid transaction should return true",
//        result = financeTrackerImp.add(
//            Transaction(
//                id = 1,
//                type = TransactionType.INCOME,
//                amount = 1000.0,
//                category = Category("food"),
//                date = LocalDate.now()
//            )
//        ),
//        correctResult = true
//    )
//
//    checkAddTransaction(
//        name = "when given a transaction with amount naegative should return false",
//        result = financeTrackerImp.add(
//            Transaction(
//                id = 2,
//                type = TransactionType.EXPENSES,
//                amount = -1000.0,
//                category = Category("food"),
//                date = LocalDate.now()
//            )
//        ),
//        correctResult = false
//    )
//
//    checkAddTransaction(
//        name = "when given a transaction with blank category should return false",
//        result = financeTrackerImp.add(
//            Transaction(
//                id = 3,
//                type = TransactionType.EXPENSES,
//                amount = 1000.0,
//                category = Category(""),
//                date = LocalDate.now()
//            )
//        ),
//        correctResult = false
//    )
//
//    checkAddTransaction(
//        name = "when given a transaction with future date should return false",
//        result = financeTrackerImp.add(
//            Transaction(
//                id = 4,
//                type = TransactionType.INCOME,
//                amount = 1000.0,
//                category = Category("bouns"),
//                date = LocalDate.now().plusDays(1)
//            )
//        ),
//        correctResult = false
//    )


}

fun checkAddTransaction(name: String, result: Boolean, correctResult: Boolean) {
    if (result == correctResult)
        println("Success - $name")
    else
        println("Faild - $name")
}