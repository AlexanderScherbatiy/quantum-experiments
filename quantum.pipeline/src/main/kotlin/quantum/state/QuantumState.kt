package quantum.state

import quantum.complex.Complex
import quantum.pipeline.QuantumState


/**
 * Qubit: |0>
 */
object QubitZero : QuantumState

/**
 * Qubit: |1>
 */
object QubitOne : QuantumState

/**
 * Qubit:
 * zero * |0> + one * |1>
 */
data class Qubit(val zero: Complex, val one: Complex) : QuantumState
