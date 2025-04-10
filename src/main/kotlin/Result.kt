sealed interface Result<out T> {
    data class Error(val cause: String? = null) : Result<Unit>
    data class Success<T>(val data: T) : Result<T>
}