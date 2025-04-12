import kotlinx.serialization.Serializable

@Serializable
data class Summary(
    val income: Double,
    val expenses: Double,
    val remaining: Double
)
