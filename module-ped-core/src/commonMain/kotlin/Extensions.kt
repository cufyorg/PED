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

/**
 * Return a string derived from this one with
 * its content tagged with the given language [tag].
 */
@PEDMarker3
infix fun String.lang(tag: String): String {
    if (tag.isEmpty()) return this
    return "$this#$tag"
}

/**
 * Return a field codec derived from this one with
 * its name tagged with the given language [tag].
 */
@PEDMarker3
infix fun <I, O> FieldCodec<I, O>.lang(tag: String): FieldCodec<I, O> {
    if (tag.isEmpty()) return this
    return FieldCodec("$name#$tag", this)
}

/**
 * Return a field codec derived from this one with
 * its name tagged with the given language [tag].
 */
@PEDMarker3
infix fun <I, O> NullableFieldCodec<I, O>.lang(tag: String): NullableFieldCodec<I, O> {
    if (tag.isEmpty()) return this
    return FieldCodec("$name#$tag", this)
}
