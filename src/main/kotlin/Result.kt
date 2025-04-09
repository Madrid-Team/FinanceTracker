sealed interface Result {
    data class Error(val cause: String) : Result
    data object Success : Result
}