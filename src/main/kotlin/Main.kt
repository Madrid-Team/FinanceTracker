import common.isValidCategory
import common.parseDate
import java.lang.Exception
import java.time.LocalDate

fun main() {




}
private fun add() {
    var amount: Double? = null
    var type: TransactionType? = null
    var transaction: Transaction? = null
    var category: Category? = null


    println("Add transaction amount")
    amount = readln().toDouble()
    try {
        if (amount <= 0)
            println("amount is not valid - Enter a number bigger than zero")
    } catch (e: Exception) {
        println("amount must be a valid value")

    }
    println("Add transaction type")
    println(
        """
        Enter 1 for -> Income  
        Enter 2 for -> Expenses
        
        """.trimIndent()

    )

    val input = readln().toInt()
    type = when (input) {
        1 -> TransactionType.INCOME
        2 -> TransactionType.EXPENSES
        else -> {
            println("Enter a valid value - either 1 for income or 2 for expenses")
            return
        }
    }
    //add transaction category
    println("add transaction category")
    val categoryInput = readln()
    if (categoryInput.isValidCategory()){
        category = Category(name = categoryInput)
    }else {
        println("please enter a valid category")
        return
    }

    //id
    val id = FinanceTrackerImpl.transactions.size + 1


    transaction = Transaction(id,type,amount,category,date = LocalDate.now())
    val transactions = FinanceTrackerImpl.transactions
    transactions.add(transaction)






}

private fun edit() {

    var category: String? = null
    var amount: Double? = null
    var type: TransactionType? = null
    var date: LocalDate? = null
    var option:Int? = null
    var transaction:Transaction? = null

    do {
        println("Please enter transaction id:")
        val input = readln()
        try {
            val id = input.toInt()
            transaction = FinanceTrackerImpl.getTransactionById(id)
            if(transaction == null){
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
                            category = Category(name = category!! )
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
                        when(input){
                            0 ->{
                                type = TransactionType.INCOME
                                transaction = transaction?.copy(
                                    type = type
                                )
                            }
                            1->{
                                type = TransactionType.EXPENSES
                                transaction = transaction?.copy(
                                    type = type
                                )
                            }
                            else ->{
                                println("please enter only 0 or 1")
                            }
                        }
                    } catch (e: Exception) {
                        println("please enter only 0 or 1")
                    }
                } while (type == null)
            }

            4 ->{

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