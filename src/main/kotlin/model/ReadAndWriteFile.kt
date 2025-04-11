package model

import Transaction
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

import kotlinx.serialization.decodeFromString
import java.time.LocalDate
class ReadAndWriteFile{
companion object {
    val filePath: String = "C:\\Users\\Abdou\\Desktop\\Android\\FinanceTracker\\src\\main\\kotlin\\model\\FinanceTrackerFile.txt"

}

}
fun loadTransactionsFromFile(filePath: String): List<Transaction> {
    val file = File(filePath)
    if (!file.exists()) return emptyList()

    val jsonString = file.readText()
  val loadedTransaction:List<Transaction> =Json.decodeFromString(jsonString)
    println(loadedTransaction)
    return loadedTransaction
}

fun saveTransactionsToFile(transactions: List<Transaction>, filePath: String) {

    var jsonString :String = Json.encodeToString(transactions)

    println("${jsonString} ")
    File(filePath).writeText("${jsonString} ")
}