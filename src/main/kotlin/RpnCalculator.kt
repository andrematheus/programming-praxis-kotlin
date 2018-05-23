import java.io.InputStream
import java.util.*

interface RpnCalculatorValue<T> {
    fun fromString(s: String): T?
    fun print(t: T)
}

class RpnCalculator<T : Any>(
    private val v: RpnCalculatorValue<T>,
    init: RpnCalculator<T>.() -> Unit
) {

    private data class Operation<T>(val arity: Int, val fn: (List<T>) -> T)

    private val operations = mutableMapOf<String, Operation<T>>()

    private val s: Stack<T> = Stack()

    init {
        init()
    }

    val top: T
        get() = s.peek()

    infix fun String.calls(fn: (T, T) -> T) {
        operations[this] = Operation(2, { xs -> fn(xs[0], xs[1]) })
    }

    private fun doOperation(op: Operation<T>) {
        val l = generateSequence { s.pop() }
            .take(op.arity)
            .toList()
            .reversed()
        s.push(op.fn(l))
    }

    private fun evaluateToken(token: String) {
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
            evaluateToken(it)
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

object DoubleValue : RpnCalculatorValue<Double> {
    override fun fromString(s: String) = s.toDouble()

    override fun print(t: Double) {
        println("%.4f".format(t))
    }
}

val floatCalculator = RpnCalculator(DoubleValue) {
    "+" calls Double::plus
    "-" calls Double::minus
    "*" calls Double::times
    "/" calls Double::div
}

fun main(args: Array<String>) {
    floatCalculator.evaluateInputStream(System.`in`)
}