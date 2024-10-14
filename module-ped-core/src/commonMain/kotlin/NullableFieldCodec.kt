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

/* ============= ------------------ ============= */

/**
 * A codec specifically for the value of some field.
 * It stores two things, the name of the field, and
 * how to encode and decode its values.
 *
 * @param I the type of the decoded value.
 * @param O the type of the encoded value.
 * @author LSafer
 * @since 2.0.0
 */
interface NullableFieldCodec<I, O> : NullableCodec<I, O>, FieldCodec<I?, O>

/**
 * Create a new [FieldCodec] with the given [name]
 * and backed by the given [codec].
 */
@Suppress("FunctionName")
fun <I, O> FieldCodec(name: String, codec: NullableCodec<I, O>): NullableFieldCodec<I, O> {
    return object : NullableFieldCodec<I, O>, NullableCodec<I, O> by codec {
        override val name = name
    }
}

/* ============= ------------------ ============= */

/**
 * Return a new codec backed by this codec that returns the given [defaultValue] when decoding fails.
 */
@PEDMarker3
infix fun <I, O> NullableFieldCodec<I, O>.defaultIn(defaultValue: I): NullableFieldCodec<I, O> {
    val codec = this
    return object : NullableFieldCodec<I, O> {
        override val name = codec.name

        override fun encode(value: Any?): Result<O> {
            return codec.encode(value)
        }

        override fun decode(value: Any?): Result<I?> {
            return runCatching {
                codec.decode(value).getOrDefault(defaultValue)
            }
        }
    }
}

/**
 * Return a new codec backed by this codec that returns the result of invoking the given [block] when decoding fails.
 */
@PEDMarker3
infix fun <I, O> NullableFieldCodec<I, O>.catchIn(block: (Throwable) -> I): NullableFieldCodec<I, O> {
    val codec = this
    return object : NullableFieldCodec<I, O> {
        override val name = codec.name

        override fun encode(value: Any?): Result<O> {
            return codec.encode(value)
        }

        override fun decode(value: Any?): Result<I?> {
            return runCatching {
                codec.decode(value).getOrElse(block)
            }
        }
    }
}

/**
 * Return a new codec backed by this codec that returns the given [defaultValue] when encoding fails.
 */
@PEDMarker3
infix fun <I, O> NullableFieldCodec<I, O>.defaultOut(defaultValue: O): NullableFieldCodec<I, O> {
    val codec = this
    return object : NullableFieldCodec<I, O> {
        override val name = codec.name

        override fun encode(value: Any?): Result<O> {
            return runCatching {
                codec.encode(value).getOrDefault(defaultValue)
            }
        }

        override fun decode(value: Any?): Result<I?> {
            return codec.decode(value)
        }
    }
}

/**
 * Return a new codec backed by this codec that returns the result of invoking the given [block] when encoding fails.
 */
@PEDMarker3
infix fun <I, O> NullableFieldCodec<I, O>.catchOut(block: (Throwable) -> O): NullableFieldCodec<I, O> {
    val codec = this
    return object : NullableFieldCodec<I, O> {
        override val name = codec.name

        override fun encode(value: Any?): Result<O> {
            return runCatching {
                codec.encode(value).getOrElse(block)
            }
        }

        override fun decode(value: Any?): Result<I?> {
            return codec.decode(value)
        }
    }
}

/* ============= ------------------ ============= */

/**
 * Create a new field codec with the given [name]
 * and backed by [this] codec.
 */
@PEDMarker3
infix fun <I, O> NullableCodec<I, O>.at(name: String): NullableFieldCodec<I, O> {
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
infix fun <I, O> String.be(codec: NullableCodec<I, O>): NullableFieldCodec<I, O> {
    return FieldCodec(this, codec)
}

/**
 * Create a new field codec with the receiver name
 * plus a dot (`.`) plus the name of [other] and
 * backed by the codec of [other].
 */
@PEDMarker2
infix fun <I, O> FieldCodec<*, *>.dot(other: NullableFieldCodec<I, O>): NullableFieldCodec<I, O> {
    return FieldCodec("${this.name}.${other.name}", other)
}

/* ============= ------------------ ============= */
