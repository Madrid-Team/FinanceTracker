import common.isValidCategory

import java.time.LocalDate
import java.time.Month
import java.util.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

object FinanceTrackerImpl : FinanceTracker, TransactionStorage {

    private val _transactions: MutableList<Transaction> = mutableListOf()


    override fun add(transaction: Transaction): Boolean {
        if (transaction.amount <= 0) return false
        if (transaction.category.name.isBlank()) return false
        if (transaction.date.isAfter(LocalDate.now())) return false
        else {
            _transactions.add(transaction)
            return true
        }
    }

    fun getTransactionById(transactionId: Int): Transaction? {
        return _transactions.find { it.id == transactionId }
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
            return str.trim()
        }
    }


    override fun editTransaction(
        transaction: Transaction,
    ): String {
        return when {
            transaction.amount <= 0 -> "Amount must be greater than 0"
            !transaction.category.name.isValidCategory() -> "please provide a valid category name with more than 3 characters and no special characters"
            transaction.date.isAfter(LocalDate.now()) -> "Date cannot be in the Future"
            transaction.date.isBefore(LocalDate.now().minusYears(2)) -> "Date is too far in the past"
            else -> {
                val transactionIndex = _transactions.indexOfFirst { it.id == transaction.id }
                return if (transactionIndex != -1) {
                    _transactions[transactionIndex] = transaction
                    "edit operation succeeded"
                } else {
                    "Transaction not found"
                }
            }
        }

    }

    override fun deleteTransaction(transactionId: Int): Boolean {
        return _transactions.removeIf { it.id == transactionId }
    }

    override fun getMonthlySummary(month: Int?, year: Int): Summary {
        if (!_transactions.isNullOrEmpty()) {
            val monthTransaction =
                _transactions.filter {
                    if (month != null && month in 0..11) it.date.month.toString()
                        .toInt() == month && it.date.year == year else it.date.year == year
                }
            val totalIncome = monthTransaction.filter { it.type == TransactionType.INCOME }.sumOf { it.amount }
            val totalExpenses = monthTransaction.filter { it.type == TransactionType.EXPENSES }.sumOf { it.amount }
            val remaining = totalIncome - totalExpenses
            return Summary(income = totalIncome, expenses = totalExpenses, remaining = remaining)
        } else {
            return Summary(income = 0.0, expenses = 0.0, remaining = 0.0)
        }
    }

    override fun viewMostcategory(month: Int?, year: Int, transaction: List<Transaction>): String {
        if (transaction.isNotEmpty()) {
            val monthTransaction: List<Transaction> = transaction.filter {
                if (month != null && month in 0..11) it.date.month.toString().toInt() == month && it.date.year == year
                else it.date.year == year
            }
            val mostFrequent = monthTransaction
                .groupingBy { it.category.name }
                .eachCount()
                .maxByOrNull { it.value }
            return "most category is  ${mostFrequent?.key} appears ${mostFrequent?.value}"
        } else {
            return "The List is empty "
        }

    }

    override fun viewMinCategory(month: Int?, year: Int, transaction: List<Transaction>): String {
        if (transaction.isNotEmpty()) {
            val monthTransaction: List<Transaction> = transaction.filter {
                if (month != null && month in 0..11) it.date.month.toString().toInt() == month && it.date.year == year
                else it.date.year == year
            }

            val leastFrequent = monthTransaction
                .groupingBy { it.category.name }
                .eachCount()
                .minByOrNull { it.value }
            return "most category is  ${leastFrequent?.key} appears ${leastFrequent?.value}"
        } else {
            return "The List is empty "
        }
    }

    private val filePath = "transactions.json"

    override fun saveTransactions(transactions: Transaction) {
        val file = File(filePath)

        val existing = if (file.exists()) {
            val json = file.readText()
            Json.decodeFromString<List<Transaction>>(json)
        } else {
            emptyList()
        }

        val allTransactions = (existing + transactions)
            .distinctBy { it.id }


        val updatedJson = Json.encodeToString(allTransactions)
        file.writeText(updatedJson)
    }

    override fun loadTransactions(): List<Transaction> {
        val file = File(filePath)
        if (!file.exists()) return emptyList()
        val json = file.readText()
        return Json.decodeFromString(json)
    }
}