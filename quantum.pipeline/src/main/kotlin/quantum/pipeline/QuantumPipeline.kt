package quantum.pipeline

interface QuantumState

interface QuantumGate

val QUANTUM_PIPELINE_OUTPUT_STATE = "quantum.pipeline.output.state"

class QuantumPipeline(val state: QuantumState, vararg gates: QuantumGate) {
    val gates = gates
}

interface QuantumCompiledPipeline

interface QuantumRunner {

    fun compile(pipeline: QuantumPipeline, variables: Map<String, Any> = mapOf()): QuantumCompiledPipeline

    fun run(pipeline: QuantumPipeline, variables: Map<String, Any> = mapOf()): Map<String, Any> =
        run(compile(pipeline, variables))

    fun run(compiledPipeline: QuantumCompiledPipeline, variables: Map<String, Any> = mapOf()): Map<String, Any>
}
