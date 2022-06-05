package quantum.sample.gate

import org.junit.Test
import quantum.complex.toComplex
import quantum.gate.QuantumGateNot
import quantum.pipeline.QUANTUM_PIPELINE_OUTPUT_STATE
import quantum.pipeline.QuantumPipeline
import quantum.sample.QuantumSampleRunner
import quantum.state.Qubit
import quantum.state.QubitOne
import quantum.state.QubitZero
import kotlin.test.assertEquals

class SampleQuantumGateNot {

    @Test
    fun testGateNotZero() {
        val runner = QuantumSampleRunner()

        val results = runner.run(QuantumPipeline(QubitZero, QuantumGateNot))
        assertEquals(QubitOne, results[QUANTUM_PIPELINE_OUTPUT_STATE])
    }

    @Test
    fun testGateNotOne() {
        val runner = QuantumSampleRunner()

        val results = runner.run(QuantumPipeline(QubitZero, QuantumGateNot))
        assertEquals(QubitOne, results[QUANTUM_PIPELINE_OUTPUT_STATE])
    }

    @Test
    fun testGateNotQubit() {
        val runner = QuantumSampleRunner()

        val z0 = (3.0 / 5.0).toComplex()
        val z1 = (4.0 / 5.0).toComplex()

        val results = runner.run(QuantumPipeline(Qubit(z0, z1), QuantumGateNot))
        assertEquals(Qubit(z1, z0), results[QUANTUM_PIPELINE_OUTPUT_STATE])
    }

    @Test
    fun testGateNotQubitRange() {
        val runner = QuantumSampleRunner()

        val z0 = (3.0 / 5.0).toComplex()
        val z1 = (4.0 / 5.0).toComplex()

        val results = runner.run(QuantumPipeline(Qubit(z0, z1), QuantumGateNot))
        assertEquals(Qubit(z1, z0), results[QUANTUM_PIPELINE_OUTPUT_STATE])
    }
}
