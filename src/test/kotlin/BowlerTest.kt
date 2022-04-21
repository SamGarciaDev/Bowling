import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BowlerTest {
    @Test
    fun `perfect game, should return 300`() {
        val bowler = Bowler()

        while (!bowler.isGameOver()) {
            bowler.roll(10, 0)
        }

        val result = bowler.getTotalScore()
        val expected = 300

        assertEquals(result, expected)
    }

    @Test
    fun `example game, should return 188`() {
        val bowler = Bowler()

        bowler.roll(10, 0)
        bowler.roll(3, 7)
        bowler.roll(8, 1)
        bowler.roll(10, 0)
        bowler.roll(10, 0)
        bowler.roll(10, 0)
        bowler.roll(6, 0)
        bowler.roll(10, 0)
        bowler.roll(10, 0)
        bowler.roll(3, 7)
        bowler.roll(10, 0)

        val result = bowler.getTotalScore()
        val expected = 188

        assertEquals(result, expected)
    }
}