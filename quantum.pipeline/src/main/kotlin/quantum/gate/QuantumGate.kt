package quantum.gate

import quantum.pipeline.QuantumGate

interface QuantumMeasure : QuantumGate

/**
 * Not
 *
 * ( 0 1 )
 * ( 1 0 )
 *
 * a |0> + b |1> -> b |0> + a |1>
 */

object QuantumGateNot : QuantumGate

class QuantumGateMeasure(val name: String, vararg indices: Int) : QuantumMeasure {
    val indices = indices
}