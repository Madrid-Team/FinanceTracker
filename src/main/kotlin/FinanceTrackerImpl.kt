import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

object FinanceTrackerImpl : FinanceTracker, TransactionStorage {

    private val _transactions: MutableList<Transaction> = mutableListOf()
    val transactions = _transactions.toList()

    override fun add(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun viewAllTransactions(): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun editTransaction(transactionId: Int) {
        TODO("Not yet implemented")
    }

    override fun deleteTransaction(transactionId: Int) {
        TODO("Not yet implemented")
    }

    override fun getSummary(): Summary {
        TODO("Not yet implemented")
    }

    private val filePath = "transactions.json"

    override fun saveTransactions(transactions: List<Transaction>) {
        val json = Json.encodeToString(transactions)
        File(filePath).writeText(json)
    }

    override fun loadTransactions(): List<Transaction> {
        val file = File(filePath)
        if (!file.exists()) return emptyList()
        val json = file.readText()
        return Json.decodeFromString(json)
    }
}