
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import model.LocalDateSerializer
import java.time.LocalDate
import java.util.Date
@Serializable
data class Transaction (
    val id:Int,
    val type:TransactionType,
    val amount:Double,
    val category:Category,
    @Serializable(with = LocalDateSerializer::class)
    val date: LocalDate,
)