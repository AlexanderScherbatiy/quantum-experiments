package quantum.complex

interface Complex

object ComplexZero : Complex

object ComplexOne : Complex

data class CartesianComplex(val real: Double, val imaginary: Double = 0.0) : Complex

fun complex(real: Double, imaginary: Double = 0.0): CartesianComplex =
    CartesianComplex(real, imaginary)

fun Int.toComplex(): Complex = complex(this.toDouble(), 0.0)

fun Double.toComplex(): Complex = complex(this, 0.0)
