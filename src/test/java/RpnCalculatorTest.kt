import org.testng.Assert.*
import org.testng.annotations.Test
import java.util.*

class RpnCalculatorTest {
    @Test
    fun testSiteExample() {
        floatCalculator.evaluate("19 2.14 + 4.5 2 4.3 / - *")
        assertEquals(floatCalculator.top, 85.2974, 0.0001)
    }
}