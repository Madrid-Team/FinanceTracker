sealed interface FinanceTrackerEvents {
    data class EditTransaction(
        val transaction :Transaction,
    ) : FinanceTrackerEvents

}