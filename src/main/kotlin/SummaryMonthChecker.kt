fun main() {
}

fun check(name: String, result: Summary, correctResult: Summary){
    if (result == correctResult){
        println("Success $name")
    }else{
        println("Failed $name")
    }
}