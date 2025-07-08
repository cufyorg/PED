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
 * A builder building a basic [Codec] implementation.
 *
 */
@PEDMarker2
interface CodecBuilder<I, O> {
    /**
     * The encoding block.
     *
     * > Do not set directly. Use `encode {}` instead.
     */
    var encodeBlock: (Any?) -> Result<O>

    /**
     * The decoding block.
     *
     * > Do not set directly. Use `decode {}` instead.
     */
    var decodeBlock: (Any?) -> Result<I>

    /**
     * Build the codec.
     *
     * @since 2.0.0
     */
    fun build(): Codec<I, O>
}

/**
 * Obtain a new [CodecBuilder] instance.
 */
fun <I, O> CodecBuilder(): CodecBuilder<I, O> {
    return object : CodecBuilder<I, O> {
        override lateinit var encodeBlock: (Any?) -> Result<O>
        override lateinit var decodeBlock: (Any?) -> Result<I>

        override fun build(): Codec<I, O> {
            if (!::encodeBlock.isInitialized)
                error("encodeBlock is required but was not set.")
            if (!::decodeBlock.isInitialized)
                error("decodeBlock is required but was not set.")

            val localEncodeBlock = encodeBlock
            val localDecodeBlock = decodeBlock
            return object : Codec<I, O> {
                override fun encode(value: Any?) = localEncodeBlock(value)
                override fun decode(value: Any?) = localDecodeBlock(value)
            }
        }
    }
}

/**
 * Create a new codec configured using the given [block].
 */
fun <I, O> Codec(block: context(CodecBuilder<I, O>) () -> Unit): Codec<I, O> {
    val builder = CodecBuilder<I, O>()
    builder.apply(block)
    return builder.build()
}

/* ============= ------------------ ============= */

// Encode-Any

/**
 * Set the encode block to be the given [block].
 *
 * @see tryInlineCodecAny
 */
@PEDMarker3
@Deprecated("Will be removed in the future")
context(builder: CodecBuilder<I, O>)
fun <I, O> encodeAny(block: (Any?) -> Result<O>) {
    builder.encodeBlock = block
}

/**
 * Set the encode block to be the given [block].
 *
 * Any error thrown by [block] will be caught,
 * wrapped with [CodecException] then returned as
 * a failure.
 *
 * @see tryInlineCodecAnyCatching
 */
@PEDMarker3
@Deprecated("Will be removed in the future")
context(builder: CodecBuilder<I, O>)
fun <I, O> encodeAnyCatching(block: (Any?) -> O) {
    builder.encodeBlock = { tryInlineCodecAnyCatching(it, block) }
}

// Encode

/**
 * Set the encode block to be the given [block].
 *
 * Any error thrown by [block] will fall through
 * uncaught.
 *
 * The value will be safely casted to type [I].
 * A failure with a [CodecException] will be returned
 * when casting failed.
 *
 * @see tryInlineCodec
 */
@PEDMarker3
context(builder: CodecBuilder<I, O>)
inline fun <reified I, O> encode(crossinline block: (I) -> Result<O>) {
    builder.encodeBlock = { tryInlineCodec(it, block) }
}

/**
 * Set the encode block to be the given [block].
 *
 * Any error thrown by [block] will be caught,
 * wrapped with [CodecException] then returned as
 * a failure.
 *
 * The value will be safely casted to type [I].
 * A failure with a [CodecException] will be returned
 * when casting failed.
 *
 * @see tryInlineCodecCatching
 */
@PEDMarker3
context(builder: CodecBuilder<I, O>)
inline fun <reified I, O> encodeCatching(crossinline block: (I) -> O) {
    builder.encodeBlock = { tryInlineCodecCatching(it, block) }
}

/* ============= ------------------ ============= */

// Decode-Any

/**
 * Set the decode block to be the given [block].
 *
 * @see tryInlineCodecAny
 */
@PEDMarker3
@Deprecated("Will be removed in the future")
context(builder: CodecBuilder<I, O>)
fun <I, O> decodeAny(block: (Any?) -> Result<I>) {
    builder.decodeBlock = block
}

/**
 * Set the decode block to be the given [block].
 *
 * Any error thrown by [block] will be caught,
 * wrapped with [CodecException] then returned as
 * a failure.
 *
 * @see tryInlineCodecAnyCatching
 */
@PEDMarker3
@Deprecated("Will be removed in the future")
context(builder: CodecBuilder<I, O>)
fun <I, O> decodeAnyCatching(block: (Any?) -> I) {
    builder.decodeBlock = { tryInlineCodecAnyCatching(it, block) }
}

// Decode

/**
 * Set the decode block to be the given [block].
 *
 * Any error thrown by [block] will fall through
 * uncaught.
 *
 * The value will be safely casted to type [I].
 * A failure with a [CodecException] will be returned
 * when casting failed.
 *
 * @see tryInlineCodec
 */
@PEDMarker3
context(builder: CodecBuilder<I, O>)
inline fun <I, reified O> decode(crossinline block: (O) -> Result<I>) {
    builder.decodeBlock = { tryInlineCodec(it, block) }
}

/**
 * Set the decode block to be the given [block].
 *
 * Any error thrown by [block] will be caught,
 * wrapped with [CodecException] then returned as
 * a failure.
 *
 * The value will be safely casted to type [I].
 * A failure with a [CodecException] will be returned
 * when casting failed.
 *
 * @see tryInlineCodecCatching
 */
@PEDMarker3
context(builder: CodecBuilder<I, O>)
inline fun <I, reified O> decodeCatching(crossinline block: (O) -> I) {
    builder.decodeBlock = { tryInlineCodecCatching(it, block) }
}

/* ============= ------------------ ============= */
