package quantum.sample.gate

import quantum.state.Qubit

fun randomQubit(): Qubit {
    val z0 = randomGoldenComplex()
    val z1 = randomGoldenComplex()

    val len = z0.length(z1)

    val n0 = z0 / len
    val n1 = z1 / len

    return Qubit(n0.toComplex(), n1.toComplex())
}
