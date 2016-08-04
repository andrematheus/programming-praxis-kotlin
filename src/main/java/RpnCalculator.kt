import java.io.InputStream
import java.util.*

interface RpnCalculatorValue<T> {
    fun fromString(s: String): T?
    fun print(t: T)
}

class RpnCalculator<T>(val v: RpnCalculatorValue<T>,
                       init: RpnCalculator<T>.() -> Unit) {

    private data class Operation<T>(val arity: Int, val fn: (List<T>) -> T)
    private val operations = mutableMapOf<String, Operation<T>>()

    val s: Stack<T> = Stack<T>()

    init {
        init()
    }

    val top: T
        get() = s.peek()

    fun operation(name: String, arity: Int, fn: (List<T>) -> T) {
        operations[name] = Operation(arity, fn)
    }

    fun binop(fn: (T, T) -> T) = { l: List<T> ->
        val (x, y) = l
        fn(x, y)
    }

    private fun doOperation(op: Operation<T>) {
        val l = mutableListOf<T>()
        for (i in 1..op.arity) {
            l.add(s.pop())
        }
        s.push(op.fn(l))
    }

    private fun evaluate_token(token: String) {
        val operation = operations[token]
        if (operation != null) {
            doOperation(operation)
        } else {
            val op = v.fromString(token)
            if (op != null) {
                s.push(op)
            } else {
                throw RuntimeException("Invalid Token: $token")
            }
        }
    }

    fun evaluate(expr: String) {
        expr.split(" ").forEach {
            evaluate_token(it)
        }
        v.print(s.peek())
    }

    fun evaluateInputStream(i: InputStream) {
        val sc = Scanner(i)
        while (sc.hasNextLine()) {
            evaluate(sc.nextLine())
        }
    }
}

object floatValue : RpnCalculatorValue<Double> {
    override fun fromString(s: String) = s.toDouble()

    override fun print(t: Double) {
        println("%.4f".format(t))
    }
}

val floatCalculator = RpnCalculator(floatValue) {
    operation("+", 2, binop { x, y -> y + x })
    operation("-", 2, binop { x, y -> y - x })
    operation("*", 2, binop { x, y -> y * x })
    operation("/", 2, binop { x, y -> y / x })
}

fun main(args: Array<String>) {
    floatCalculator.evaluateInputStream(System.`in`)
}