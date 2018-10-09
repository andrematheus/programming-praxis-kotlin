import org.junit.Test
import kotlin.test.assertEquals

class BabbageNumberTest {
    @Test
    fun `babbage number should end in 269696`() {
        val number = BabbageNumber().intValue
        val squared = number * number
        assertEquals("269696", squared.toString().takeLast(6),
            "Result should be a babbage Number")
        print("Babbage Number is: $number")
    }
}