package quantum.sample.gate

import org.junit.Test
import quantum.bit.Bits
import quantum.gate.QuantumGateMeasure
import quantum.pipeline.QUANTUM_PIPELINE_OUTPUT_STATE
import quantum.pipeline.QuantumPipeline
import quantum.sample.QuantumSampleRunner
import quantum.state.QubitOne
import quantum.state.QubitZero
import kotlin.test.assertEquals

class SampleQuantumGateMeasureTest {

    val LOOP = 10

    @Test
    fun testMeasureQubitZero() {
        val measureQubitName = "measure.qubit.name.0"
        val runner = QuantumSampleRunner()
        val pipeline = QuantumPipeline(QubitZero, QuantumGateMeasure(measureQubitName))
        for (i in 1..LOOP) {
            val results = runner.run(pipeline)
            assertEquals(QubitZero, results[QUANTUM_PIPELINE_OUTPUT_STATE])
            assertEquals(Bits(false), results[measureQubitName])
        }
    }

    @Test
    fun testMeasureQubitOne() {
        val measureQubitName = "measure.qubit.name.1"
        val runner = QuantumSampleRunner()
        val pipeline = QuantumPipeline(QubitOne, QuantumGateMeasure(measureQubitName))
        for (i in 1..LOOP) {
            val results = runner.run(pipeline)
            assertEquals(QubitOne, results[QUANTUM_PIPELINE_OUTPUT_STATE])
            assertEquals(Bits(true), results[measureQubitName])
        }
    }
}