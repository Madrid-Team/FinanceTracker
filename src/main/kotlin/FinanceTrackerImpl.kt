

class FinanceTrackerImpl(logger: Logger) : FinanceTracker, Logger by logger {

    private val _transactions: MutableList<Transaction> = mutableListOf()
    val transactions = _transactions.toList()

    override fun add(transaction: Transaction) {
        println("Added transaction ${transaction.date}")
        log("Added transaction: ${transaction}")
    }

    override fun viewAllTransactions(transactions: List<Transaction>): String {
        if (transactions.isEmpty()) return "No transactions found."
        else {
            var str = ""
            for (trasaction in transactions) {
                str += " ID: ${trasaction.id}\n"
                str += " Type: ${trasaction.type}\n"
                str += " Amount: ${trasaction.amount}\n"
                str += " Category: ${trasaction.category.name}\n"
                str += " Date: ${trasaction.date}\n"
            }
            val viewTransaction = str.trim()
            println(viewTransaction)
            log("This is all Transaction: $viewTransaction")
            return str.trim()
        }
    }

    override fun editTransaction(transactionId: Int) {
        println("Edited Transaction of ID $transactionId")
        log("Edited Transaction of ID $transactionId")
    }

    override fun deleteTransaction(transactionId: Int) {
        println("Deleted Transaction of ID $transactionId")
        log("Deleted Transaction of ID $transactionId")
    }

    override fun getMonthlySummary(month: Int?, year: Int): Summary {
    if(!transactions.isNullOrEmpty()) {
        val monthTransaction =
            _transactions.filter { if (month != null && month in 0..11) it.date.month == month && it.date.year == year else it.date.year == year }
        val totalIncome = monthTransaction.filter { it.type == TransactionType.INCOME }.sumOf { it.amount }
        val totalExpenses = monthTransaction.filter { it.type == TransactionType.EXPENSES }.sumOf { it.amount }
        val remaining = totalIncome - totalExpenses

        println("Income $totalIncome Expenses: $totalExpenses Remaining: $remaining")
        log("Summary of $month Month is:  Income $totalIncome Expenses: $totalExpenses Remaining: $remaining ")
        return Summary(income = totalIncome, expenses = totalExpenses, remaining = remaining)
    }
        else{
            return  Summary(income = 0.0, expenses = 0.0, remaining = 0.0)
        }
    }
     override fun viewMostcategory(month: Int?, year: Int ,transaction :List<Transaction> ):String{
      if (transaction.isNotEmpty()) {
          val monthTransaction: List<Transaction> = transaction.filter {
              if (month != null && month in 0..11)     it.date.month == month && it.date.year == year
              else  it.date.year == year
          }
          val mostFrequent = monthTransaction
              .groupingBy { it.category.name }
              .eachCount()
              .maxByOrNull { it.value }
          return "most category is  ${mostFrequent?.key} appears ${mostFrequent?.value}"
      }else {
          return "The List is empty "
      }

     }

    override fun viewMinCategory(month: Int?, year: Int, transaction: List<Transaction>): String {
        if (transaction.isNotEmpty()) {
            val monthTransaction: List<Transaction> = transaction.filter {
                if (month != null && month in 0..11)     it.date.month == month && it.date.year == year
                else  it.date.year == year
            }

            val leastFrequent = monthTransaction
                .groupingBy { it.category.name }
                .eachCount()
                .minByOrNull { it.value }
            return "most category is  ${leastFrequent?.key} appears ${leastFrequent?.value}"
        }else {
            return "The List is empty "
        }    }

}