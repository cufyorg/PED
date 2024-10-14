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

/**
 * Return a field codec derived from this one with
 * its name tagged with the given language [tag].
 */
@PEDMarker3
@DeprecatedWithContextParameters
infix fun <I> BsonFieldCodec<I>.lang(tag: String): BsonFieldCodec<I> {
    if (tag.isEmpty()) return this
    return FieldCodec("$name#$tag", this)
}

/**
 * Return a field codec derived from this one with
 * its name tagged with the given language [tag].
 */
@PEDMarker3
@DeprecatedWithContextParameters
infix fun <I> BsonNullableFieldCodec<I>.lang(tag: String): BsonNullableFieldCodec<I> {
    if (tag.isEmpty()) return this
    return FieldCodec("$name#$tag", this)
}

/**
 * Create an instance [I] from first constructing a [BsonDocument] with
 * the given [block] then decoding it with [this] codec.
 */
inline operator fun <I> BsonCodec<I>.invoke(block: BsonDocumentBlock): I {
    return BsonDocument(block) decode this
}

/**
 * Get the value of the field with the name of the
 * given [codec] and decode it using the given [codec].
 */
operator fun <I> BsonDocumentLike.get(codec: FieldCodec<I, BsonElement>): I {
    return this[codec.name] decodeAny codec
}
