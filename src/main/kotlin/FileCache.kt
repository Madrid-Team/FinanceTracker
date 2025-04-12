import java.io.File
import java.util.Date

class FileCache: Logger  {

    override fun log(message: String) {
        val file = File("transactions.txt")
        file.appendText("$message\n")
    }
}