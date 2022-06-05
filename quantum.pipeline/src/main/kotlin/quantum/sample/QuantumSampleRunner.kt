package quantum.sample

import quantum.bit.Bits
import quantum.complex.CartesianComplex
import quantum.complex.Complex
import quantum.complex.ComplexOne
import quantum.complex.ComplexZero
import quantum.gate.QuantumGateNot
import quantum.gate.QuantumGateMeasure
import quantum.pipeline.*
import quantum.state.Qubit
import quantum.state.QubitOne
import quantum.state.QubitZero
import kotlin.random.Random

class QuantumSampleCompiledPipeline(val state: QuantumState, vararg gates: QuantumGate) : QuantumCompiledPipeline {
    val gates = gates
}

class QuantumSampleRunner : QuantumRunner {

    override fun compile(pipeline: QuantumPipeline, variables: Map<String, Any>): QuantumSampleCompiledPipeline {
        return QuantumSampleCompiledPipeline(pipeline.state, *pipeline.gates)
    }

    override fun run(compiledPipeline: QuantumCompiledPipeline, variables: Map<String, Any>): Map<String, Any> {

        if (compiledPipeline !is QuantumSampleCompiledPipeline) {
            throw Exception("Compiled pipeline is not instance of QuantumSampleCompiledPipeline: $compiledPipeline")
        }

        val resutls = mutableMapOf<String, Any>()
        var state = compiledPipeline.state
        for (gate in compiledPipeline.gates) {
            state = applyGate(state, gate, resutls)
        }
        resutls[QUANTUM_PIPELINE_OUTPUT_STATE] = state
        return resutls
    }
}

data class ResultStateBits(val state: QuantumState, val bits: Bits)

data class QuantumSampleException(val msg: String) : Exception(msg)

private fun applyGate(state: QuantumState, gate: QuantumGate, variables: MutableMap<String, Any>): QuantumState =
    when (gate) {
        is QuantumGateNot -> applyGateNot(state)
        is QuantumGateMeasure -> applyGateMeasure(state, gate.name, variables)
        else -> throw QuantumSampleException("Unknown quantum gate: $gate")
    }

private fun applyGateNot(state: QuantumState): QuantumState = when (state) {
    is QubitZero -> QubitOne
    is QubitOne -> QubitZero
    is Qubit -> Qubit(state.one, state.zero)
    else -> throw QuantumSampleException("Unknown quantum state: $state")
}

private fun applyGateMeasure(
    state: QuantumState, name: String,
    variables: MutableMap<String, Any>
): QuantumState = when (state) {
    is QubitZero -> {
        variables[name] = Bits(false)
        QubitZero
    }
    is QubitOne -> {
        variables[name] = Bits(true)
        QubitOne
    }
    is Qubit -> {
        val resultStateBits = measureQubit(state)
        variables[name] = resultStateBits.bits
        resultStateBits.state
    }
    else -> throw QuantumSampleException("Unknown quantum state: $state")
}

private fun measureQubit(qubit: Qubit): ResultStateBits =
    if (sqr(qubit.zero) < randomZeroOne())
        ResultStateBits(QubitZero, Bits(false))
    else
        ResultStateBits(QubitOne, Bits(true))


private fun randomZeroOne(): Double = random(0.0, 1.0)

private fun random(min: Double, max: Double): Double = Random.nextDouble(min, max)

private fun sqr(complex: Complex): Double = when (complex) {
    is ComplexZero -> 0.0
    is ComplexOne -> 1.0
    is CartesianComplex -> complex.real * complex.real + complex.imaginary + complex.imaginary
    else -> throw QuantumSampleException("Unknown complex: $complex")
}
