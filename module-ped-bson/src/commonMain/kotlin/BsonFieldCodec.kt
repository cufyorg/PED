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

import org.cufy.bson.BsonElement
import org.cufy.bson.DeprecatedWithContextParameters
import org.cufy.bson.MutableBsonMapField

/* ============= ------------------ ============= */

/**
 * A bson variant of [FieldCodec] enabling extra
 * features that can be achieved only when the
 * target output is known to be bson.
 *
 * This implements [MutableBsonMapField] to enable the following syntax:
 * ```kotlin
 * document {
 *      MyField by myValue
 * }
 * ```
 *
 * This interface will be useless after context
 * receivers is released for production.
 * This interface will be removed gradually after
 * context receivers is released for production.
 *
 * @param I the type of the decoded value.
 * @param O the type of the encoded value.
 * @author LSafer
 * @since 2.0.0
 */
@DeprecatedWithContextParameters
interface BsonFieldCodec<I> : FieldCodec<I, BsonElement>, MutableBsonMapField<I> {
    override fun encode(value: I): BsonElement =
        encode(value, this)
}

/**
 * Create a new field codec with the given [name]
 * and backed by the given [codec].
 */
@Suppress("FunctionName")
@DeprecatedWithContextParameters
fun <I> FieldCodec(name: String, codec: BsonCodec<I>): BsonFieldCodec<I> {
    return object : BsonFieldCodec<I>, BsonCodec<I> by codec {
        override val name = name
    }
}

/* ============= ------------------ ============= */

/**
 * Return a new codec backed by this codec that returns the given [defaultValue] when decoding fails.
 */
@PEDMarker3
@DeprecatedWithContextParameters
infix fun <I> BsonFieldCodec<I>.defaultIn(defaultValue: I): BsonFieldCodec<I> {
    val codec = this as BsonCodec<I>
    return FieldCodec(name, codec defaultIn defaultValue)
}

/**
 * Return a new codec backed by this codec that returns the result of invoking the given [block] when decoding fails.
 */
@PEDMarker3
@DeprecatedWithContextParameters
infix fun <I> BsonFieldCodec<I>.catchIn(block: (Throwable) -> I): BsonFieldCodec<I> {
    val codec = this as BsonCodec<I>
    return FieldCodec(name, codec catchIn block)
}

/**
 * Return a new codec backed by this codec that returns the given [defaultValue] when encoding fails.
 */
@PEDMarker3
@DeprecatedWithContextParameters
infix fun <I> BsonFieldCodec<I>.defaultOut(defaultValue: BsonElement): BsonFieldCodec<I> {
    val codec = this as BsonCodec<I>
    return FieldCodec(name, codec defaultOut defaultValue)
}

/**
 * Return a new codec backed by this codec that returns the result of invoking the given [block] when encoding fails.
 */
@PEDMarker3
@DeprecatedWithContextParameters
infix fun <I> BsonFieldCodec<I>.catchOut(block: (Throwable) -> BsonElement): BsonFieldCodec<I> {
    val codec = this as BsonCodec<I>
    return FieldCodec(name, codec catchOut block)
}

/* ============= ------------------ ============= */

/**
 * Create a new field codec with the given [name]
 * and backed by [this] codec.
 */
@PEDMarker3
@DeprecatedWithContextParameters
infix fun <I> BsonCodec<I>.at(name: String): BsonFieldCodec<I> {
    return FieldCodec(name, this)
}

/**
 * Create a new field codec with the receiver name
 * and backed by the given [codec].
 *
 * Example:
 *
 * ```
 * object User {
 *      val Name = "name" be Codecs.String
 *      val Age = "age" be Codecs.Int64.Nullable
 * }
 * ```
 */
@PEDMarker3
@DeprecatedWithContextParameters
infix fun <I> String.be(codec: BsonCodec<I>): BsonFieldCodec<I> {
    return FieldCodec(this, codec)
}

/**
 * Create a new field codec with the receiver name
 * plus a dot (`.`) plus the name of [other] and
 * backed by the codec of [other].
 */
@PEDMarker2
@DeprecatedWithContextParameters
infix fun <I> FieldCodec<*, *>.dot(other: BsonFieldCodec<I>): BsonFieldCodec<I> {
    return FieldCodec("${this.name}.${other.name}", other)
}

/* ============= ------------------ ============= */
