fun main() {

    val finance = FinanceTrackerImpl
    val result = finance.getMonthSummary(month = 3, year = 2010)

    val correctResultSummary = Summary(
        income = 500.0,
        expenses = 300.0,
        remaining = 200.0
    )
    check("Correct Month Summary as expected ", result, correctResultSummary)
    val emptyMonthSummary = Summary(
        income = 0.0,
        expenses = 0.0,
        remaining = 0.0
    )
    check("Empty Month Summary ", result, emptyMonthSummary)
    val allIncomeSummary = Summary(
        income = 2000.0,
        expenses = 0.0,
        remaining = 2000.0
    )
    check("All Income Month Summary ", result, allIncomeSummary)

    val mixedSummary = Summary(
        income = 1200.0,
        expenses = 500.0,
        remaining = 700.0
    )
    check("Mixed Transactions ", result, mixedSummary)
    val allExpensesSummary = Summary(
        income = 0.0,
        expenses = 750.0,
        remaining = -750.0
    )
    check("All Expense Month Summary ", result, allExpensesSummary)
    val remainingSummary = Summary(
        income = 600.0,
        expenses = 600.0,
        remaining = 0.0
    )
    check("Balanced Month Summary ", result, remainingSummary)
}

fun check(name: String, result: Summary, correctResult: Summary){
    if (result == correctResult){
        println("Success $name")
    }else{
        println("Failed $name")
    }
}