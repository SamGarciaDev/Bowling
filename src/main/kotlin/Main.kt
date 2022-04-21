import kotlin.math.floor

fun main() {
}

fun randomInt(max: Int): Int {
    return floor(Math.random() * (max + 1)).toInt()
}