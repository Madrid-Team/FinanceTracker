import java.time.LocalDate

data class Transaction(
    val id:Int,
    val type:TransactionType,
    val amount:Double,
    val category:Category,
    val date: LocalDate,
)