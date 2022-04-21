import kotlin.math.min

class Bowler {
    private val score = arrayListOf<ScoreUnit>()
    private var currentFrame = 1

    /**
     * Registers the throws in a frame.
     * If the first shot was a strike, it ignores the second. It throws an exception if the game is already over.
     */
    fun roll(first: Int, second: Int) {
        if (isGameOver()) throw IllegalStateException("The game is over!")

        score.add(
            ScoreUnit(
                frame = currentFrame,
                ball = 1,
                score = first
            )
        )

        if (first == 10) {
            currentFrame++
            return
        }

        score.add(
            ScoreUnit(
                frame = currentFrame,
                ball = 2,
                score = min(10 - first, second)
            )
        )

        currentFrame++
    }

    /**
     * Calculates the total score of the game.
     * If the print parameter is set to true, it prints the score sheet.
     */
    fun getTotalScore(print: Boolean = true) : Int {
        val scoreSheet = Array(11) {0}

        for ((i, s) in score.withIndex()) {
            if (s.frame > 10) break

            scoreSheet[s.frame] += s.score

            if (s.score == 10) {
                scoreSheet[s.frame] += score[i + 1].score
                scoreSheet[s.frame] += score[i + 2].score
                continue
            }

            if (s.ball == 2 && score[i - 1].score + score[i].score == 10) {
                scoreSheet[s.frame] += score[i + 1].score
            }
        }

        for (i in scoreSheet.indices) {
            if (i == 0) continue
            scoreSheet[i] += scoreSheet[i - 1]
        }

        if (print) {
            for (s in score) {
                if (s.frame > 10) break
                println("${s.frame}, ${s.ball}: ${s.score} [${scoreSheet[s.frame]}]")
            }
        }

        return scoreSheet[10]
    }

    /**
     * Determines if the game is over or not.
     */
    fun isGameOver(): Boolean {
        val inStrike = (currentFrame == 11 && score[score.size - 1].score == 10) || (currentFrame == 12 && score[score.size - 2].score == 10)
        val inSpare = !inStrike && currentFrame == 11 && (score[score.size - 1].score + score[score.size - 2].score == 10)

        return currentFrame > 12 || (currentFrame > 10 && !(inStrike || inSpare))
    }
}