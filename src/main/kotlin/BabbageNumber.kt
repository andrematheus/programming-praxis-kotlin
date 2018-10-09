class BabbageNumber {
    val intValue: Int by lazy {
        generateBabbageNumber()
    }

    private fun generateBabbageNumber() =
        generateSequence(0) { it + 1}.first(::isBabbageNumber)

    private fun isBabbageNumber(n: Int): Boolean {
        val squared = n * n
        return squared.toString().takeLast(6) == "269696"
    }
}

fun main(args: Array<String>) {
    print("Babbage number is ${BabbageNumber().intValue}")
}
