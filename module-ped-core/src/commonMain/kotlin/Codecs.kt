package org.cufy.ped

import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success

/* ============= ------------------ ============= */

/**
 * A codec simplifying enum encoding.
 */
class EnumCodec<I, O>(private val pairs: List<Pair<I, O>>) : Codec<I, O> {
    constructor(vararg pairs: Pair<I, O>) : this(pairs.asList())

    override fun encode(value: Any?): Result<O> {
        return pairs.firstOrNull { it.first == value }.let {
            when (it) {
                null -> failure(CodecException("Enum mismatch: $value"))
                else -> success(it.second)
            }
        }
    }

    override fun decode(value: Any?): Result<I> {
        return pairs.firstOrNull { it.second == value }.let {
            when (it) {
                null -> failure(CodecException("Enum mismatch: $value"))
                else -> success(it.first)
            }
        }
    }
}

/* ============= ------------------ ============= */
