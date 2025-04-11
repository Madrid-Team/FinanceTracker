import java.time.LocalDate
import java.util.Date

class PrintTransactoin : Logger {
    override fun log(message: String) {
        println("Time of Transaction ${Date()} $message")
    }
}