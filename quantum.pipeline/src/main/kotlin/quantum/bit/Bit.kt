package quantum.bit

class Bits(vararg values: Boolean) {
    val values = values

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other is Bits) {
            return this.values.contentEquals(other.values)
        }
        return false
    }

    override fun hashCode(): Int {
        return values.contentHashCode()
    }
}
