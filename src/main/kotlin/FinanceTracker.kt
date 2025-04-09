import model.Summary

interface FinanceTracker {
    fun getMonthlySummary(month: Int, year: Int): Summary
}