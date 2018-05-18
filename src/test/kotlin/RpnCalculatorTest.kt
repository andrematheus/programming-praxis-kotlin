import org.junit.Assert
import org.junit.Test

class RpnCalculatorTest {
    @Test
    fun testSiteExample() {
        floatCalculator.evaluate("19 2.14 + 4.5 2 4.3 / - *")
        Assert.assertEquals(floatCalculator.top, 85.2974, 0.0001)
    }
}