import kotlinx.serialization.Serializable

@Serializable
data class Transaction(
    val id: Int,
    val type: TransactionType,
    val amount: Double,
    val category: Category,
    val date: String, //change from date
)