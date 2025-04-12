import FinanceTrackerImpl.loadTransactions
import common.isValidCategory
import common.parseDate
import java.lang.Exception
import java.time.LocalDate

fun main() {


    val tracker = FinanceTrackerImpl
    val transaction = Transaction(
        7,
        TransactionType.INCOME, 1500.0,
        Category("Freelance"),
        LocalDate.now()
    )
    tracker.saveTransactions(transaction)

    val loaded = loadTransactions()
    loaded.forEach {
        println("- ${it.id} : ${it.type} .. ${it.amount} .. ${it.category} .. ${it.date}")
    }
}


private fun edit() {

    var category: String? = null
    var amount: Double? = null
    var type: TransactionType? = null
    var date: LocalDate? = null
    var option: Int? = null
    var transaction: Transaction? = null

    do {
        println("Please enter transaction id:")
        val input = readln()
        try {
            val id = input.toInt()
            transaction = FinanceTrackerImpl.getTransactionById(id)
            if (transaction == null) {
                println("Id not exist")
            }
        } catch (e: Exception) {
            println("id must be number")
        }
    } while (transaction == null)


    while (true) {
        println(
            """
                
            Enter 1 for edit the amount
            Enter 2 for edit the category
            Enter 3 for edit the type
            Enter 4 for edit the date
            Enter 5 for exit
            
        """.trimIndent()
        )
        println("selected option:")
        try {
            option = readln().toInt()
        } catch (e: Exception) {
            continue
        }

        when (option) {
            1 -> {
                do {
                    println("Please enter new amount:")
                    try {
                        amount = readln().toDouble()

                        transaction = transaction?.copy(
                            amount = amount
                        )
                    } catch (e: Exception) {
                        println("amount must be number")
                    }
                } while (amount == null)
            }

            2 -> {
                do {
                    println("Please enter new category:")
                    try {
                        val input = readln()
                        category = if (input.isValidCategory()) input else null
                        transaction = transaction?.copy(
                            category = Category(name = category!!)
                        )
                    } catch (e: Exception) {
                        println("amount must be valid string")
                    }
                } while (category == null)
            }

            3 -> {
                do {
                    println(
                        """
                            
                            Please enter new type:
                            0 for INCOME
                            1 for EXPENSES
                            
                        """.trimIndent()
                    )
                    try {
                        val input = readln().toInt()
                        when (input) {
                            0 -> {
                                type = TransactionType.INCOME
                                transaction = transaction?.copy(
                                    type = type
                                )
                            }

                            1 -> {
                                type = TransactionType.EXPENSES
                                transaction = transaction?.copy(
                                    type = type
                                )
                            }

                            else -> {
                                println("please enter only 0 or 1")
                            }
                        }
                    } catch (e: Exception) {
                        println("please enter only 0 or 1")
                    }
                } while (type == null)
            }

            4 -> {

                do {
                    println("Please enter valid date (yy-mm-dd):")
                    try {
                        val input = readln()
                        date = parseDate(input)
                        transaction = transaction?.copy(
                            date = date
                        )
                    } catch (e: Exception) {
                        println("please enter valid date with this format yy-mm-dd")
                    }
                } while (date == null)
            }

            5 -> {
                transaction?.let {
                    FinanceTrackerImpl.editTransaction(transaction)
                }
                break
            }

        }
    }


}