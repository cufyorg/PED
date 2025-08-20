package org.cufy.ped

import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success
import kotlin.enums.enumEntries

/* ============= ------------------ ============= */

inline fun <reified I : Enum<I>, O> EnumCodec(block: (I) -> O): EnumCodec<I, O> {
    return EnumCodec(enumEntries<I>().map { it to block(it) })
}

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

@ExperimentalPEDApi
fun <I> buildStringCodec(decode: (String) -> I): Codec<I, String> {
    return Codec {
        val builder = contextOf()
        builder.encodeBlock = {
            success(it.toString())
        }
        decodeCatching(decode)
    }
}

/* ============= ------------------ ============= */
