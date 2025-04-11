import java.io.File
import java.util.Date

class FileCache: Logger  {

    override fun log(message: String) {
        val file = File("transaction.txt")
        file.appendText("Transaction Time ${Date()} $message")

    }
}