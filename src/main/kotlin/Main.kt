import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.JsonPrimitive
import model.ReadAndWriteFile
import model.loadTransactionsFromFile
import model.saveTransactionsToFile
import java.time.LocalDate



fun main() {
    val transactions: List<Transaction> = listOf<Transaction>(
        Transaction(
            1,
            TransactionType.INCOME,
            1500.0,
            Category("Salary"), LocalDate.of(2025, 8, 5)
        ),
        Transaction(
            2,
            TransactionType.INCOME,
            10500.0,
            Category("Salary2"), LocalDate.of(2025, 9, 5)
        )
    )




    saveTransactionsToFile(transactions,ReadAndWriteFile.filePath)
    val pr: List<Transaction> = loadTransactionsFromFile(ReadAndWriteFile.filePath)
    //println(pr)
}


