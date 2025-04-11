import java.time.LocalDate
import java.util.Date

data class Transaction(
    val id:Int,
    val type:TransactionType,
    val amount:Double,
    val category:Category,
    val date: Date,
)