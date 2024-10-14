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

import kotlin.js.JsName
import kotlin.jvm.JvmName

/* ============= ------------------ ============= */

/**
 * Decode the given [value] to [O] using the given [codec].
 *
 * @param value the value to decode.
 * @param codec the codec to be used.
 * @return the decoding result.
 * @since 2.0.0
 */
@JvmName("tryDecodeNullish")
@JsName("tryDecodeNullish")
@PEDMarker2
inline fun <I, O> tryDecode(value: O?, codec: NullableCodec<I, O>): Result<I?> {
    return tryDecodeAny(value, codec)
}

/**
 * Decode the given [value] to [O] using the given [codec].
 *
 * @param value the value to decode.
 * @param codec the codec to be used.
 * @return the decoding result.
 * @since 2.0.0
 */
@JvmName("tryDecodeNullable")
@JsName("tryDecodeNullable")
@PEDMarker2
inline fun <I, O> tryDecode(value: O, codec: NullableCodec<I, O>): Result<I?> {
    return tryDecodeAny(value, codec)
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
@JvmName("decodeNullish")
@JsName("decodeNullish")
@PEDMarker2
inline fun <I, O> decode(value: O?, codec: NullableCodec<I, O>): I? {
    return decodeAny(value, codec)
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
@JvmName("decodeNullable")
@JsName("decodeNullable")
@PEDMarker2
inline fun <I, O> decode(value: O, codec: NullableCodec<I, O>): I? {
    return decodeAny(value, codec)
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
@JvmName("decodeInfixNullish")
@JsName("decodeInfixNullish")
@PEDMarker3
inline infix fun <I, O> O?.decode(codec: NullableCodec<I, O>): I? {
    return decode(this, codec)
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
@JvmName("decodeInfixNullable")
@JsName("decodeInfixNullable")
@PEDMarker3
inline infix fun <I, O> O.decode(codec: NullableCodec<I, O>): I? {
    return decode(this, codec)
}

/* ============= ------------------ ============= */
