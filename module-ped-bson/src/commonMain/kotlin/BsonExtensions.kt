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

typealias BsonDocumentCodecBlock<I> = context(BsonDocumentBuilder, BsonCodec<I>) () -> Unit

fun <I> BsonDocument(codec: BsonCodec<I>, block: BsonDocumentCodecBlock<I>) =
    BsonDocument { context(codec) { block() } }

/**
 * Create an instance [I] from first constructing a [BsonDocument] with
 * the given [block] then decoding it with [this] codec.
 */
inline operator fun <I> BsonCodec<I>.invoke(block: BsonDocumentBlock): I {
    return decode(BsonDocument(block)).getOrElse { throw it.toCodecException() }
}

/**
 * Get the value of the field with the name of the
 * given [codec] and decode it using the given [codec].
 */
operator fun <I> BsonDocumentLike.get(codec: BsonFieldCodec<I>): I {
    val element = this[codec.name]
    return codec.decode(element).getOrElse { throw it.toCodecException() }
}

operator fun <I> MutableBsonDocumentLike.set(codec: BsonFieldCodec<I>, value: I) {
    put(codec.name, value encode codec)
}

infix fun <T> T.encodeOne(codec: BsonCodec<List<T>>): BsonElement =
    ((listOf(this) encode codec) as BsonArray)[0]

infix fun <T> BsonElement.decodeOne(codec: BsonCodec<List<T>>): T =
    (BsonArray(this) decode codec).single()

context(builder: BsonDocumentBuilder)
infix fun <I> BsonFieldCodec<I>.by(value: I) {
    builder[this.name] = value encode this
}

context(builder: BsonDocumentBuilder)
infix fun <I> BsonFieldCodec<I>.by(block: BsonDocumentCodecBlock<I>) {
    builder[this.name] = BsonDocument(codec, block)
}

context(builder: BsonDocumentBuilder)
infix fun <I> BsonFieldCodec<List<I>>.byOne(value: I) {
    builder[this.name] = value encodeOne this
}

context(builder: BsonDocumentBuilder)
infix fun <I> BsonFieldCodec<List<I>>.byOne(block: BsonDocumentCodecBlock<I>) {
    builder[this.name] = BsonDocument(codec.Single, block)
}
