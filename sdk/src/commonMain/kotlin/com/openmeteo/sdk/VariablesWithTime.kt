// automatically generated by the FlatBuffers compiler, do not modify

package com.openmeteo.sdk

import com.google.flatbuffers.kotlin.*
import kotlin.jvm.JvmInline
@Suppress("unused")
class VariablesWithTime : Table() {

    fun init(i: Int, buffer: ReadWriteBuffer) : VariablesWithTime = reset(i, buffer)

    val time : Long get() = lookupField(4, 0L ) { bb.getLong(it + bufferPos) }

    val timeEnd : Long get() = lookupField(6, 0L ) { bb.getLong(it + bufferPos) }

    val interval : Int get() = lookupField(8, 0 ) { bb.getInt(it + bufferPos) }

    fun variables(j: Int) : com.openmeteo.sdk.VariableWithValues? = variables(com.openmeteo.sdk.VariableWithValues(), j)
    fun variables(obj: com.openmeteo.sdk.VariableWithValues, j: Int) : com.openmeteo.sdk.VariableWithValues? = lookupField(10, null ) { obj.init(indirect(vector(it) + j * 4), bb) }
    val variablesLength : Int get() = lookupField(10, 0 ) { vectorLength(it) }

    companion object {
        fun validateVersion() = VERSION_2_0_8

        fun asRoot(buffer: ReadWriteBuffer) : VariablesWithTime = asRoot(buffer, VariablesWithTime())
        fun asRoot(buffer: ReadWriteBuffer, obj: VariablesWithTime) : VariablesWithTime = obj.init(buffer.getInt(buffer.limit) + buffer.limit, buffer)


        fun createVariablesWithTime(builder: FlatBufferBuilder, time: Long, timeEnd: Long, interval: Int, variablesOffset: VectorOffset<com.openmeteo.sdk.VariableWithValues>) : Offset<VariablesWithTime> {
            builder.startTable(4)
            addTimeEnd(builder, timeEnd)
            addTime(builder, time)
            addVariables(builder, variablesOffset)
            addInterval(builder, interval)
            return endVariablesWithTime(builder)
        }
        fun startVariablesWithTime(builder: FlatBufferBuilder) = builder.startTable(4)

        fun addTime(builder: FlatBufferBuilder, time: Long) = builder.add(0, time, 0L)

        fun addTimeEnd(builder: FlatBufferBuilder, timeEnd: Long) = builder.add(1, timeEnd, 0L)

        fun addInterval(builder: FlatBufferBuilder, interval: Int) = builder.add(2, interval, 0)

        fun addVariables(builder: FlatBufferBuilder, variables: VectorOffset<com.openmeteo.sdk.VariableWithValues>) = builder.add(3, variables, 0)

        fun createVariablesVector(builder: FlatBufferBuilder, vector:com.openmeteo.sdk.VariableWithValuesOffsetArray) : VectorOffset<com.openmeteo.sdk.VariableWithValues> {
            builder.startVector(4, vector.size, 4)
            for (i in vector.size - 1 downTo 0) {
                builder.add(vector[i])
            }
            return builder.endVector()
        }

        fun startVariablesVector(builder: FlatBufferBuilder, numElems: Int) = builder.startVector(4, numElems, 4)

        fun endVariablesWithTime(builder: FlatBufferBuilder) : Offset<VariablesWithTime> {
            val o: Offset<VariablesWithTime> = builder.endTable()
            return o
        }
    }
}

typealias VariablesWithTimeOffsetArray = OffsetArray<VariablesWithTime>

inline fun VariablesWithTimeOffsetArray(size: Int, crossinline call: (Int) -> Offset<VariablesWithTime>): VariablesWithTimeOffsetArray =
    VariablesWithTimeOffsetArray(IntArray(size) { call(it).value })
