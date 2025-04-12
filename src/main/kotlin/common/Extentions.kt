package common

fun String.isValidCategory():Boolean{
    return this.length in 3..10 && this.contains("^[a-zA-Z]+$".toRegex())
}