fun main() {
    val tracker = FinanceTrackerImpl
}

private fun deleteTransaction() {
    println("Please enter transaction with id: ")

    lable1@ do {
        try {
            val idTransaction = readln().toInt()
            val transactionDeleted = FinanceTrackerImpl.deleteTransaction(idTransaction)
            if (transactionDeleted) {
                println("Transaction deleted is successful")
                break@lable1
            } else {
                println("Transaction not found do you want to continue?(y/n)")
                do {
                    try {
                        val option = readln()
                        if (option == "n") {
                            break@lable1
                        } else if (option == "y") {
                            println("Done")
                            break
                        }
                    }catch (e:Exception){
                        println("Please enter valid option: (y/n)")
                    }
                }while (true)

            }
        } catch (e: Exception) {
            println("Please enter valid transaction id: ")
        }
    } while (true)


}