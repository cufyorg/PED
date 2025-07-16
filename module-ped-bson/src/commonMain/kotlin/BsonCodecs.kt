/*
 *	Copyright 2023 cufy.org and meemer.com
 *
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *
 *	    http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 */
package org.cufy.ped

import org.cufy.bson.*
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success
import kotlin.time.Instant

/* ============= ------------------ ============= */

typealias BsonCodec<I> = Codec<I, BsonElement>
typealias BsonFieldCodec<I> = FieldCodec<I, BsonElement>
typealias BsonEnumCodec<I> = EnumCodec<I, BsonElement>
typealias BsonCodecBuilder<I> = CodecBuilder<I, BsonElement>

/* ============= ------------------ ============= */

fun <I> BsonCodecBuilder(): BsonCodecBuilder<I> {
    return CodecBuilder()
}

fun <I> BsonCodec(block: context(BsonCodecBuilder<I>) () -> Unit): BsonCodec<I> {
    return Codec(block)
}

/* ============= ------------------ ============= */

/**
 * A codec that always decodes nullish values
 * to `null` and encodes `null` to [BsonNull] and
 * uses the given [codec] otherwise.
 *
 * Nullish values includes `null`, [BsonNull] and [BsonUndefined]
 *
 * @author LSafer
 * @since 2.0.0
 */
class BsonNullableCodec<I>(val codec: BsonCodec<I>) : BsonCodec<I?> {
    companion object {
        fun <I> of(codec: BsonCodec<I>): BsonCodec<I?> {
            if (codec is BsonNullableCodec<*>)
                @Suppress("UNCHECKED_CAST")
                return codec as BsonNullableCodec<I>
            return BsonNullableCodec(codec)
        }
    }

    override fun encode(value: Any?) = when (value) {
        null -> success(BsonNull)
        else -> codec.encode(value)
    }

    override fun decode(value: Any?) = when (value) {
        null, BsonNull, BsonUndefined -> success(null)
        else -> codec.decode(value)
    }
}

/**
 * Obtain a codec that always decodes nullish
 * values to `null` and encodes `null`
 * to [BsonNull] and uses this codec otherwise.
 *
 * Nullish values includes `null`, [BsonNull] and [BsonUndefined]
 */
val <I> BsonCodec<I>.Nullable: BsonCodec<I?>
    get() = BsonNullableCodec.of(this)

/**
 * Obtain a codec that always decodes nullish
 * values to `null` and encodes `null`
 * to [BsonNull] and uses this codec otherwise.
 *
 * Nullish values includes `null`, [BsonNull] and [BsonUndefined]
 */
val <I> BsonFieldCodec<I>.Nullable: BsonFieldCodec<I?>
    get() = FieldCodec(this.name, BsonNullableCodec.of(this.codec))

/* ============= ------------------ ============= */

/**
 * A codec for [List] and [BsonArray] that uses
 * the given [codec] to encode/decode each
 * individual item.
 */
class BsonArrayCodec<I>(val codec: BsonCodec<I>) : BsonCodec<List<I>> {
    companion object {
        fun <I> of(codec: BsonCodec<I>): BsonCodec<List<I>> {
            return BsonArrayCodec(codec)
        }
    }

    override fun encode(value: Any?) = tryInlineCodec(value) { it: List<*> ->
        success(BsonArray {
            it.mapTo(contextOf()) {
                codec.encode(it).getOrElse { e ->
                    return@tryInlineCodec failure(e)
                }
            }
        })
    }

    override fun decode(value: Any?) = tryInlineCodec(value) { it: BsonArray ->
        success(it.map {
            codec.decode(it).getOrElse { e ->
                return@tryInlineCodec failure(e)
            }
        })
    }
}

/**
 * Obtain a codec for [List] and [BsonArray] that
 * uses this codec to encode/decode each
 * individual item.
 */
val <I> BsonCodec<I>.Array: BsonCodec<List<I>>
    get() = BsonArrayCodec.of(this)

/**
 * Obtain a codec for [List] and [BsonArray] that
 * uses this codec to encode/decode each
 * individual item.
 */
val <I> BsonFieldCodec<I>.Array: BsonFieldCodec<List<I>>
    get() = FieldCodec(this.name, BsonArrayCodec.of(this.codec))

/* ============= ------------------ ============= */

/**
 * Pass-through codec for [BsonDocument].
 */
object BsonDocumentCodec : BsonCodec<BsonDocument> {
    override fun encode(value: Any?) = tryInlineCodec(value) { it: BsonDocument -> success(it) }
    override fun decode(value: Any?) = tryInlineCodec(value) { it: BsonDocument -> success(it) }
}

/* ============= ------------------ ============= */

/**
 * The codec for [String] and [BsonString].
 *
 * @since 2.0.0
 */
object BsonStringCodec : BsonCodec<String> {
    override fun encode(value: Any?) = tryInlineCodec(value) { it: String -> success(BsonString(it)) }
    override fun decode(value: Any?) = tryInlineCodec(value) { it: BsonString -> success(it.value) }
}

/* ============= ------------------ ============= */

/**
 * The codec for [Boolean] and [BsonBoolean].
 *
 * @since 2.0.0
 */
object BsonBooleanCodec : BsonCodec<Boolean> {
    override fun encode(value: Any?) = tryInlineCodec(value) { it: Boolean -> success(BsonBoolean(it)) }
    override fun decode(value: Any?) = tryInlineCodec(value) { it: BsonBoolean -> success(it.value) }
}

/* ============= ------------------ ============= */

/**
 * The codec for [Int] and [BsonNumber].
 *
 * @since 2.0.0
 */
object BsonInt32Codec : BsonCodec<Int> {
    override fun encode(value: Any?) = tryInlineCodec(value) { it: Int -> success(BsonInt32(it)) }
    override fun decode(value: Any?) = tryInlineCodec(value) { it: BsonNumber -> success(it.toInt()) }
}

/* ============= ------------------ ============= */

/**
 * The codec for [Long] and [BsonNumber].
 *
 * @since 2.0.0
 */
object BsonInt64Codec : BsonCodec<Long> {
    override fun encode(value: Any?) = tryInlineCodec(value) { it: Long -> success(BsonInt64(it)) }
    override fun decode(value: Any?) = tryInlineCodec(value) { it: BsonNumber -> success(it.toLong()) }
}

/* ============= ------------------ ============= */

/**
 * The codec for [Double] and [BsonNumber].
 *
 * @since 2.0.0
 */
object BsonDoubleCodec : BsonCodec<Double> {
    override fun encode(value: Any?) = tryInlineCodec(value) { it: Double -> success(BsonDouble(it)) }
    override fun decode(value: Any?) = tryInlineCodec(value) { it: BsonNumber -> success(it.toDouble()) }
}

/* ============= ------------------ ============= */

/**
 * The codec for [Decimal128] and [BsonDecimal128].
 *
 * @since 2.0.0
 */
object BsonDecimal128Codec : BsonCodec<Decimal128> {
    override fun encode(value: Any?) = tryInlineCodec(value) { it: Decimal128 -> success(BsonDecimal128(it)) }
    override fun decode(value: Any?) = tryInlineCodec(value) { it: BsonNumber -> success(it.toDecimal128()) }
}

/**
 * The codec for [Long] and [BsonDateTime].
 *
 * @since 2.0.0
 */
object BsonDateTimeCodec : BsonCodec<Long> {
    override fun encode(value: Any?) = tryInlineCodec(value) { it: Long -> success(BsonDateTime(it)) }
    override fun decode(value: Any?) = tryInlineCodec(value) { it: BsonDateTime -> success(it.value) }
}

/* ============= ------------------ ============= */

/**
 * The codec for [Instant] and [BsonDateTime].
 *
 * @since 2.0.0
 */
object BsonInstantCodec : BsonCodec<Instant> {
    override fun encode(value: Any?) = tryInlineCodec(value) { it: Instant -> success(BsonDateTime(it)) }
    override fun decode(value: Any?) = tryInlineCodec(value) { it: BsonDateTime -> success(it.toInstant()) }
}

/* ============= ------------------ ============= */

/**
 * The codec for [ObjectId] and [BsonObjectId].
 *
 * @since 2.0.0
 */
object BsonObjectIdCodec : BsonCodec<ObjectId> {
    override fun encode(value: Any?) = tryInlineCodec(value) { it: ObjectId -> success(BsonObjectId(it)) }
    override fun decode(value: Any?) = tryInlineCodec(value) { it: BsonObjectId -> success(it.value) }
}

/* ============= ------------------ ============= */

@Deprecated("use BsonIDCodec instead", ReplaceWith("BsonIDCodec"))
typealias BsonIdCodec = BsonIDCodec

/**
 * The codec for [ID] and [BsonObjectId] or [BsonString].
 *
 * @since 2.0.0
 */
object BsonIDCodec : BsonCodec<ID<Any?>> {
    override fun encode(value: Any?) = tryInlineCodec(value) { it: ID<Any?> -> success(it.bson) }
    override fun decode(value: Any?) =
        tryInlineCodec(value) { it: BsonElement ->
            when (it) {
                is BsonObjectId -> success(AnyID(it.value))
                is BsonString -> success(AnyID(it.value))
                else -> failure(
                    CodecException(
                        "Cannot decode ${it::class}; expected either " +
                                BsonObjectId::class + " or " +
                                BsonString::class
                    )
                )
            }
        }

    /**
     * Return this codec casted to [T].
     */
    operator fun <T> invoke(): Codec<ID<T>, BsonElement> {
        @Suppress("UNCHECKED_CAST")
        return this as Codec<ID<T>, BsonElement>
    }
}

/* ============= ------------------ ============= */
