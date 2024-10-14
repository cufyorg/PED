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
 * A codec is an encoder/decoder from/to [I] and [O].
 *
 * @param I the type of the decoded value.
 * @param O the type of the encoded value.
 * @author LSafer
 * @since 2.0.0
 */
interface Codec<I, O> {
    /**
     * Encode the given [value] to type [O].
     *
     * > Do not use this function directly. Use `encode(I, Codec)` instead.
     *
     * @param value the value to be encoded.
     * @return the encoded value.
     * @since 2.0.0
     */
    fun encode(value: Any?): Result<O>

    /**
     * Decode the given [value] value into [I].
     *
     * > Do not use this function directly. Use `decode(Codec, I)` instead.
     *
     * @param value the value to be decoded.
     * @return the decoded value.
     * @since 2.0.0
     */
    fun decode(value: Any?): Result<I>
}

/* ============= ------------------ ============= */

/**
 * Return a new codec backed by this codec that returns the given [defaultValue] when decoding fails.
 */
@PEDMarker3
infix fun <I, O> Codec<I, O>.defaultIn(defaultValue: I): Codec<I, O> {
    val codec = this
    return object : Codec<I, O> {
        override fun encode(value: Any?): Result<O> {
            return codec.encode(value)
        }

        override fun decode(value: Any?): Result<I> {
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
infix fun <I, O> Codec<I, O>.catchIn(block: (Throwable) -> I): Codec<I, O> {
    val codec = this
    return object : Codec<I, O> {
        override fun encode(value: Any?): Result<O> {
            return codec.encode(value)
        }

        override fun decode(value: Any?): Result<I> {
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
infix fun <I, O> Codec<I, O>.defaultOut(defaultValue: O): Codec<I, O> {
    val codec = this
    return object : Codec<I, O> {
        override fun encode(value: Any?): Result<O> {
            return runCatching {
                codec.encode(value).getOrDefault(defaultValue)
            }
        }

        override fun decode(value: Any?): Result<I> {
            return codec.decode(value)
        }
    }
}

/**
 * Return a new codec backed by this codec that returns the result of invoking the given [block] when encoding fails.
 */
@PEDMarker3
infix fun <I, O> Codec<I, O>.catchOut(block: (Throwable) -> O): Codec<I, O> {
    val codec = this
    return object : Codec<I, O> {
        override fun encode(value: Any?): Result<O> {
            return runCatching {
                codec.encode(value).getOrElse(block)
            }
        }

        override fun decode(value: Any?): Result<I> {
            return codec.decode(value)
        }
    }
}

/* ============= ------------------ ============= */
