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
@file:Suppress("NOTHING_TO_INLINE")

package org.cufy.ped

import kotlin.jvm.JvmName

/* ============= ------------------ ============= */

// Encode Any

/**
 * Encode the given [value] to [O] using the given [codec].
 *
 * @param value the value to encode. (type checked at runtime)
 * @param codec the codec to be used.
 * @return the encoding result.
 * @since 2.0.0
 */
@PEDMarker2
@Deprecated("Will be removed in the future")
inline fun <I, O> tryEncodeAny(value: Any?, codec: Codec<I, O>): Result<O> {
    return codec.encode(value)
}

/**
 * Encode the given [value] to [O] using the given [codec].
 *
 * @param value the value to encode. (type checked at runtime)
 * @param codec the codec to be used.
 * @return the encoded value.
 * @throws CodecException if encoding failed.
 * @since 2.0.0
 */
@PEDMarker2
@Deprecated("Will be removed in the future")
fun <I, O> encodeAny(value: Any?, codec: Codec<I, O>): O {
    return codec.encode(value).getOrElse { throw it.toCodecException() }
}

/**
 * Encode [this] value to [O] using the given [codec].
 *
 * @receiver the value to encode.
 * @param codec the codec to be used.
 * @return the encoded value.
 * @throws CodecException if encoding failed.
 * @since 2.0.0
 */
@JvmName("encodeAnyInfix")
@PEDMarker3
@Deprecated("Will be removed in the future")
inline infix fun <I, O> Any?.encodeAny(codec: Codec<I, O>): O {
    return codec.encode(this).getOrElse { throw it.toCodecException() }
}

// Encode

/**
 * Encode the given [value] to [O] using the given [codec].
 *
 * @param value the value to encode.
 * @param codec the codec to be used.
 * @return the encoding result.
 * @since 2.0.0
 */
@PEDMarker2
inline fun <I, O> tryEncode(value: I, codec: Codec<I, O>): Result<O> {
    return codec.encode(value)
}

/**
 * Encode the given [value] to [O] using the given [codec].
 *
 * @param value the value to encode.
 * @param codec the codec to be used.
 * @return the encoded value.
 * @throws CodecException if encoding failed.
 * @since 2.0.0
 */
@PEDMarker2
inline fun <I, O> encode(value: I, codec: Codec<I, O>): O {
    return codec.encode(value).getOrElse { throw it.toCodecException() }
}

/**
 * Encode [this] value to [O] using the given [codec].
 *
 * @receiver the value to encode.
 * @param codec the codec to be used.
 * @return the encoded value.
 * @throws CodecException if encoding failed.
 * @since 2.0.0
 */
@JvmName("encodeInfix")
@PEDMarker3
inline infix fun <I, O> I.encode(codec: Codec<I, O>): O {
    return codec.encode(this).getOrElse { throw it.toCodecException() }
}

/* ============= ------------------ ============= */

// Decode Any

/**
 * Decode the given [value] to [O] using the given [codec].
 *
 * @param value the value to decode. (type checked at runtime)
 * @param codec the codec to be used.
 * @return the decoding result.
 * @since 2.0.0
 */
@PEDMarker2
@Deprecated("Will be removed in the future")
inline fun <I, O> tryDecodeAny(value: Any?, codec: Codec<I, O>): Result<I> {
    return codec.decode(value)
}

/**
 * Decode the given [value] to [O] using the given [codec].
 *
 * @param value the value to decode. (type checked at runtime)
 * @param codec the codec to be used.
 * @return the decoded value.
 * @throws CodecException if decoding failed.
 * @since 2.0.0
 */
@PEDMarker2
@Deprecated("Will be removed in the future")
fun <I, O> decodeAny(value: Any?, codec: Codec<I, O>): I {
    return codec.decode(value).getOrElse { throw it.toCodecException() }
}

/**
 * Decode [this] value to [I] using the given [codec].
 *
 * @receiver the value to decode.
 * @param codec the codec to be used.
 * @return the decoded value.
 * @throws CodecException if decoding failed.
 * @since 2.0.0
 */
@JvmName("decodeAnyInfix")
@PEDMarker3
@Deprecated("Will be removed in the future")
inline infix fun <I, O> Any?.decodeAny(codec: Codec<I, O>): I {
    return codec.decode(this).getOrElse { throw it.toCodecException() }
}

// Decode

/**
 * Decode the given [value] to [O] using the given [codec].
 *
 * @param value the value to decode.
 * @param codec the codec to be used.
 * @return the decoding result.
 * @since 2.0.0
 */
@PEDMarker2
inline fun <I, O> tryDecode(value: O, codec: Codec<I, O>): Result<I> {
    return codec.decode(value)
}

/**
 * Decode the given [value] to [O] using the given [codec].
 *
 * @param value the value to decode.
 * @param codec the codec to be used.
 * @return the decoded value.
 * @throws CodecException if decoding failed.
 * @since 2.0.0
 */
@Suppress("NOTHING_TO_INLINE")
@PEDMarker2
inline fun <I, O> decode(value: O, codec: Codec<I, O>): I {
    return codec.decode(value).getOrElse { throw it.toCodecException() }
}

/**
 * Decode [this] value to [I] using the given [codec].
 *
 * @receiver the value to decode.
 * @param codec the codec to be used.
 * @return the decoded value.
 * @throws CodecException if decoding failed.
 * @since 2.0.0
 */
@Suppress("NOTHING_TO_INLINE")
@JvmName("decodeInfix")
@PEDMarker3
inline infix fun <I, O> O.decode(codec: Codec<I, O>): I {
    return codec.decode(this).getOrElse { throw it.toCodecException() }
}

/* ============= ------------------ ============= */
