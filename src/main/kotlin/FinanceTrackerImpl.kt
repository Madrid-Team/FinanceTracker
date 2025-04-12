import common.isValidCategory
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.time.LocalDate

object FinanceTrackerImpl : FinanceTracker, TransactionStorage {

    private val filePath = "transactions.json"
    private val _transactions: MutableList<Transaction> = mutableListOf()
    val transactions: List<Transaction> get() = _transactions

    init {
        // تحميل المعاملات من الملف عند بداية التشغيل
        _transactions.addAll(loadTransactions())
    }

    override fun add(transaction: Transaction): Boolean {
        if (transaction.amount <= 0) return false
        if (transaction.category.name.isBlank()) return false
        if (transaction.date.isAfter(LocalDate.now())) return false

        _transactions.add(transaction)
        saveToFile()
        return true
    }

    fun getTransactionById(transactionId: Int): Transaction? {
        return _transactions.find { it.id == transactionId }
    }

    override fun viewAllTransactions(transactions: List<Transaction>): String {
        if (transactions.isEmpty()) return "No transactions found."
        return transactions.joinToString("\n\n") {
            """
            ID: ${it.id}
            Type: ${it.type}
            Amount: ${it.amount}
            Category: ${it.category.name}
            Date: ${it.date}
            """.trimIndent()
        }
    }

    override fun editTransaction(transaction: Transaction): String {
        if (transaction.amount <= 0) return "Amount must be greater than 0"
        if (!transaction.category.name.isValidCategory()) return "Invalid category name"
        if (transaction.date.isAfter(LocalDate.now())) return "Date cannot be in the future"
        if (transaction.date.isBefore(LocalDate.now().minusYears(2))) return "Date is too far in the past"

        val index = _transactions.indexOfFirst { it.id == transaction.id }
        return if (index != -1) {
            _transactions[index] = transaction
            saveToFile()
            "Edit operation succeeded"
        } else {
            "Transaction not found"
        }
    }

    override fun deleteTransaction(transactionId: Int): Boolean {
        val removed = _transactions.removeIf { it.id == transactionId }
        if (removed) saveToFile()
        return removed
    }

    override fun deleteTransactionFromFile(transactionId: Int): String {
        return if (deleteTransaction(transactionId)) {
            "Transaction deleted successfully"
        } else {
            "Invalid ID, try again with another ID"
        }
    }

    override fun getMonthlySummary(month: Int?, year: Int): Summary {
        val filtered = _transactions.filter {
            (month == null || it.date.monthValue == month) && it.date.year == year
        }
        val income = filtered.filter { it.type == TransactionType.INCOME }.sumOf { it.amount }
        val expenses = filtered.filter { it.type == TransactionType.EXPENSES }.sumOf { it.amount }
        return Summary(income, expenses, income - expenses)
    }

    override fun viewMostCategory(month: Int?, year: Int, transaction: List<Transaction>): String {
        val filtered = transaction.filter {
            (month == null || it.date.monthValue == month) && it.date.year == year
        }
        val most = filtered.groupingBy { it.category.name }
            .eachCount()
            .maxByOrNull { it.value }

        return if (most != null) "Most category is ${most.key}, appears ${most.value}" else "No data"
    }

    override fun viewMinCategory(month: Int?, year: Int, transaction: List<Transaction>): String {
        val filtered = transaction.filter {
            (month == null || it.date.monthValue == month) && it.date.year == year
        }
        val least = filtered.groupingBy { it.category.name }
            .eachCount()
            .minByOrNull { it.value }

        return if (least != null) "Least category is ${least.key}, appears ${least.value}" else "No data"
    }

    override fun saveTransactions(transaction: Transaction) {
        add(transaction)
    }

    override fun loadTransactions(): List<Transaction> {
        val file = File(filePath)
        return if (file.exists()) {
            val json = file.readText()
            Json.decodeFromString(json)
        } else {
            emptyList()
        }
    }

    private fun saveToFile() {
        val file = File(filePath)
        file.writeText(Json.encodeToString(_transactions))
    }
}
