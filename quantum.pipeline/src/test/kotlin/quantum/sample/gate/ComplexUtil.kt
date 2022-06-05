package quantum.sample.gate

import quantum.complex.*
import java.lang.RuntimeException
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random

data class GoldenComplex(val real: Double, val imaginary: Double) {

    operator fun plus(other: GoldenComplex): GoldenComplex =
        GoldenComplex(this.real + other.real, this.imaginary + other.imaginary)

    operator fun minus(other: GoldenComplex): GoldenComplex =
        GoldenComplex(this.real - other.real, this.imaginary - other.imaginary)

    // (a1 + i b1) * (a2 + i b2) = a1 * a2 - b1 * b2 + i (a1 * b2 + b1 * a2)
    operator fun times(other: GoldenComplex): GoldenComplex =
        GoldenComplex(
            this.real * other.real - this.imaginary * other.imaginary,
            this.real * other.imaginary + this.imaginary * other.real
        )

    // (a1 + i b1) * (a2 + i b2) = a1 * a2 - b1 * b2 + i (a1 * b2 + b1 * a2)
    operator fun div(other: GoldenComplex): GoldenComplex = this * other.conjugate()

    operator fun times(value: Double): GoldenComplex =
        GoldenComplex(real * value, imaginary * value)

    operator fun div(value: Double): GoldenComplex =
        GoldenComplex(real / value, imaginary / value)

    // 1 / (a + i b) = (a - i b) (a^2 + b^2)
    fun invert(): GoldenComplex = this.conjugate() / this.lengthSqr()

    fun conjugate(): GoldenComplex = GoldenComplex(real, -imaginary)

    fun length(): Double = sqrt(lengthSqr())

    fun length(other: GoldenComplex): Double = length(this - other)

    private fun lengthSqr() = real * real + imaginary * imaginary
}

fun GoldenComplex.toComplex(): Complex = CartesianComplex(this.real, this.imaginary)

fun Complex.togoldenComplex(): GoldenComplex = when (this) {
    is ComplexZero -> GoldenComplex(0.0, 0.0)
    is ComplexOne -> GoldenComplex(1.0, 0.0)
    is CartesianComplex -> GoldenComplex(this.real, this.imaginary)
    else -> throw RuntimeException("Unknown complex: $this")
}

fun randomGoldenComplex(): GoldenComplex {
    val angle = Random.nextDouble(2 * PI)
    val c = cos(angle)
    val s = sin(angle)
    return GoldenComplex(c, s)
}
