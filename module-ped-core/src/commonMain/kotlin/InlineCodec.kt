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

// Inline Codec Any

/**
 * Invoke the given codec [block] on the given [value].
 *
 * Any error thrown by [block] will be fall through
 * this function uncaught.
 *
 * @param value the value to encode.
 * @param block the codec block to be used.
 * @return the encoding/decoding result.
 * @since 2.0.0
 */
@PEDMarker2
@Deprecated("Will be removed in the future")
inline fun <T> tryInlineCodecAny(value: Any?, block: (Any?) -> Result<T>): Result<T> {
    return block(value)
}

/**
 * Invoke the given codec [block] on the given [value].
 *
 * Any error thrown by [block] will be caught,
 * wrapped with [CodecException] then returned as
 * a failure.
 *
 * @param value the value to encode.
 * @param block the codec block to be used.
 * @return the encoding/decoding result.
 * @since 2.0.0
 */
@PEDMarker2
@Deprecated("Will be removed in the future")
inline fun <T> tryInlineCodecAnyCatching(value: Any?, block: (Any?) -> T): Result<T> {
    return try {
        Result.success(block(value))
    } catch (e: Throwable) {
        Result.failure(e.toCodecException())
    }
}

/**
 * Invoke the given codec [block] on the given [value].
 *
 * Any error thrown by [block] will fall through
 * this function uncaught.
 *
 * @param value the value to encode.
 * @param block the codec block to be used.
 * @return the encoded/decoded value.
 * @throws CodecException if encoding/decoding failed.
 * @since 2.0.0
 */
@PEDMarker2
@Deprecated("Will be removed in the future")
inline fun <T> inlineCodecAny(value: Any?, block: (Any?) -> Result<T>): T {
    return block(value).getOrElse { throw it.toCodecException() }
}

/**
 * Invoke the given codec [block] on the given [value].
 *
 * Any error thrown by [block] will rethrown as
 * [CodecException].
 *
 * @param value the value to encode.
 * @param block the codec block to be used.
 * @return the encoding result.
 * @since 2.0.0
 */
@PEDMarker2
@Deprecated("Will be removed in the future")
inline fun <T> inlineCodecAnyCatching(value: Any?, block: (Any?) -> T): T {
    return try {
        block(value)
    } catch (e: Throwable) {
        throw e.toCodecException()
    }
}

// Inline Codec

/**
 * Invoke the given codec [block] on the given [value].
 *
 * Any error thrown by [block] will fall through
 * this function uncaught.
 *
 * The given [value] will be safely casted to type [T].
 * A failure with a [CodecException] will be returned
 * when casting failed.
 *
 * @param value the value to encode. (type checked at runtime)
 * @param block the codec block to be used.
 * @return the encoding/decoding result.
 * @since 2.0.0
 */
@PEDMarker2
inline fun <reified T, U> tryInlineCodec(value: Any?, block: (T) -> Result<U>): Result<U> {
    return when (value) {
        is T -> block(value)
        else -> Result.failure(CodecException("Cannot encode/decode ${value?.let { it::class }}; expected ${T::class}"))
    }
}

/**
 * Invoke the given codec [block] on the given [value].
 *
 * Any error thrown by [block] will be caught,
 * wrapped with [CodecException] then returned as
 * a failure.
 *
 * The given [value] will be safely casted to type [T].
 * A failure with a [CodecException] will be returned
 * when casting failed.
 *
 * @param value the value to encode. (type checked at runtime)
 * @param block the codec block to be used.
 * @return the encoding/decoding result.
 * @since 2.0.0
 */
@PEDMarker2
inline fun <reified T, U> tryInlineCodecCatching(value: Any?, block: (T) -> U): Result<U> {
    return when (value) {
        is T -> try {
            Result.success(block(value))
        } catch (e: Throwable) {
            Result.failure(e.toCodecException())
        }

        else -> Result.failure(CodecException("Cannot encode/decode ${value?.let { it::class }}; expected ${T::class}"))
    }
}

/**
 * Invoke the given codec [block] on the given [value].
 *
 * Any error thrown by [block] will fall through
 * this function uncaught.
 *
 * The given [value] will be safely casted to type [T].
 * A [CodecException] will be thrown when casting failed.
 *
 * @param value the value to encode. (type checked at runtime)
 * @param block the codec block to be used.
 * @return the encoded/decoded value.
 * @throws CodecException if encoding/decoding failed.
 * @since 2.0.0
 */
@PEDMarker2
@Deprecated("Will be removed in the future")
inline fun <reified T, U> inlineCodec(value: Any?, block: (T) -> Result<U>): U {
    return when (value) {
        is T -> block(value).getOrElse { throw it.toCodecException() }
        else -> throw CodecException("Cannot encode/decode ${value?.let { it::class }}; expected ${T::class}")
    }
}

/**
 * Invoke the given codec [block] on the given [value].
 *
 * Any error thrown by [block] will be rethrown
 * as a [CodecException].
 *
 * The given [value] will be safely casted to type [T].
 * A [CodecException] will be thrown when casting failed.
 *
 * @param value the value to encode. (type checked at runtime)
 * @param block the codec block to be used.
 * @return the encoded/decoded value.
 * @throws CodecException if encoding/decoding failed.
 * @since 2.0.0
 */
@PEDMarker2
@Deprecated("Will be removed in the future")
inline fun <reified T, U> inlineCodecCatching(value: Any?, block: (T) -> U): U {
    return when (value) {
        is T -> try {
            block(value)
        } catch (e: Throwable) {
            throw e.toCodecException()
        }

        else -> throw CodecException("Cannot encode/decode ${value?.let { it::class }}; expected ${T::class}")
    }
}

/* ============= ------------------ ============= */
