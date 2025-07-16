@file:Suppress("FunctionName")

package org.cufy.ped.filters

import org.cufy.bson.BsonDocumentBuilder
import org.cufy.bson.BsonMarker2
import org.cufy.bson.array
import org.cufy.bson.by
import org.cufy.mongodb.*
import org.cufy.ped.BsonCodec
import org.cufy.ped.BsonFieldCodec
import org.cufy.ped.encode
import org.cufy.ped.encodeOne

// https://www.mongodb.com/docs/manual/reference/operator/query/#comparison

/* ============= ------------------ ============= */

/** https://www.mongodb.com/docs/manual/reference/operator/query/eq */
@BsonMarker2
@JvmName($$"(BsonCodec<T>)_$eq_T")
context(_: /* Operator */BsonDocumentBuilder, _: BsonCodec<T>)
fun <T> `$eq`(value: T) =
    `$eq` by encode(value)

/** https://www.mongodb.com/docs/manual/reference/operator/query/eq */
@Suppress("CONTEXTUAL_OVERLOAD_SHADOWED")
@BsonMarker2
@JvmName($$"(BsonCodec<List<T>>)_$eq_T")
context(_: /* Operator */BsonDocumentBuilder, _: BsonCodec<List<T>>)
fun <T> `$eq`(value: T) =
    `$eq` by encodeOne(value)

/** https://www.mongodb.com/docs/manual/reference/operator/query/eq */
@BsonMarker2
@JvmName($$"BsonFieldCodec<T>_$eq_T")
context(_: /* Query */BsonDocumentBuilder)
infix fun <T> BsonFieldCodec<T>.`$eq`(value: T) =
    this.name by { `$eq` by (value encode this) }

/** https://www.mongodb.com/docs/manual/reference/operator/query/eq */
@BsonMarker2
@JvmName($$"BsonFieldCodec<List<T>>_$eq_T")
context(_: /* Query */BsonDocumentBuilder)
infix fun <T> BsonFieldCodec<List<T>>.`$eq`(value: T) =
    this.name by { `$eq` by (value encodeOne this) }

/* ============= ------------------ ============= */

/** https://www.mongodb.com/docs/manual/reference/operator/query/gt */
@BsonMarker2
context(_: /* Operator */BsonDocumentBuilder, _: BsonCodec<T>)
fun <T> `$gt`(value: T) =
    `$gt` by encode(value)

/** https://www.mongodb.com/docs/manual/reference/operator/query/gt */
@BsonMarker2
context(_: /* Query */BsonDocumentBuilder)
infix fun <T> BsonFieldCodec<T>.`$gt`(value: T) =
    this.name by { `$gt` by (value encode this) }

/* ============= ------------------ ============= */

/** https://www.mongodb.com/docs/manual/reference/operator/query/gte */
@BsonMarker2
context(_: /* Operator */BsonDocumentBuilder, _: BsonCodec<T>)
fun <T> `$gte`(value: T) =
    `$gte` by encode(value)

/** https://www.mongodb.com/docs/manual/reference/operator/query/gte */
@BsonMarker2
context(_: /* Query */BsonDocumentBuilder)
infix fun <T> BsonFieldCodec<T>.`$gte`(value: T) =
    this.name by { `$gte` by (value encode this) }

/* ============= ------------------ ============= */

/** https://www.mongodb.com/docs/manual/reference/operator/query/in */
@BsonMarker2
context(_: /* Operator */BsonDocumentBuilder, _: BsonCodec<List<T>>)
fun <T> `$in`(vararg values: T) =
    `$in` by encode(values.asList())

/** https://www.mongodb.com/docs/manual/reference/operator/query/in */
@BsonMarker2
context(_: /* Operator */BsonDocumentBuilder, _: BsonCodec<List<T>>)
fun <T> `$in`(values: List<T>) =
    `$in` by encode(values)

/** https://www.mongodb.com/docs/manual/reference/operator/query/in */
@BsonMarker2
@JvmName($$"BsonFieldCodec<T>_$in_List<T>")
context(_: /* Query */BsonDocumentBuilder)
infix fun <T> BsonFieldCodec<T>.`$in`(values: List<T>) =
    this.name by { `$in` by array { values.forEach { by(it encode this) } } }

/** https://www.mongodb.com/docs/manual/reference/operator/query/in */
@BsonMarker2
@JvmName($$"BsonFieldCodec<List<T>>_$in_List<T>")
context(_: /* Query */BsonDocumentBuilder)
infix fun <T> BsonFieldCodec<List<T>>.`$in`(values: List<T>) =
    this.name by { `$in` by (values encode this) }

/* ============= ------------------ ============= */

/** https://www.mongodb.com/docs/manual/reference/operator/query/lt */
@BsonMarker2
context(_: /* Operator */BsonDocumentBuilder, _: BsonCodec<T>)
fun <T> `$lt`(value: T) =
    `$lt` by encode(value)

/** https://www.mongodb.com/docs/manual/reference/operator/query/lt */
@BsonMarker2
context(_: /* Query */BsonDocumentBuilder)
infix fun <T> BsonFieldCodec<T>.`$lt`(value: T) =
    this.name by { `$lt` by (value encode this) }

/* ============= ------------------ ============= */

/** https://www.mongodb.com/docs/manual/reference/operator/query/lte */
@BsonMarker2
context(_: /* Operator */BsonDocumentBuilder, _: BsonCodec<T>)
fun <T> `$lte`(value: T) =
    `$lte` by encode(value)

/** https://www.mongodb.com/docs/manual/reference/operator/query/lte */
@BsonMarker2
context(_: /* Query */BsonDocumentBuilder)
infix fun <T> BsonFieldCodec<T>.`$lte`(value: T) =
    this.name by { `$lte` by (value encode this) }

/* ============= ------------------ ============= */

/** https://www.mongodb.com/docs/manual/reference/operator/query/ne */
@BsonMarker2
@JvmName($$"(BsonCodec<T>)_$ne_T")
context(_: /* Operator */BsonDocumentBuilder, _: BsonCodec<T>)
fun <T> `$ne`(value: T) =
    `$ne` by encode(value)

/** https://www.mongodb.com/docs/manual/reference/operator/query/ne */
@Suppress("CONTEXTUAL_OVERLOAD_SHADOWED")
@BsonMarker2
@JvmName($$"(BsonCodec<List<T>>)_$ne_T")
context(_: /* Operator */BsonDocumentBuilder, _: BsonCodec<List<T>>)
fun <T> `$ne`(value: T) =
    `$ne` by encodeOne(value)

/** https://www.mongodb.com/docs/manual/reference/operator/query/ne */
@BsonMarker2
@JvmName($$"BsonFieldCodec<T>_$ne_T")
context(_: /* Query */BsonDocumentBuilder)
infix fun <T> BsonFieldCodec<T>.`$ne`(value: T) =
    this.name by { `$ne` by (value encode this) }

/** https://www.mongodb.com/docs/manual/reference/operator/query/ne */
@BsonMarker2
@JvmName($$"BsonFieldCodec<List<T>>_$ne_T")
context(_: /* Query */BsonDocumentBuilder)
infix fun <T> BsonFieldCodec<List<T>>.`$ne`(value: T) =
    this.name by { `$ne` by (value encodeOne this) }

/* ============= ------------------ ============= */

/** https://www.mongodb.com/docs/manual/reference/operator/query/nin */
@BsonMarker2
context(_: /* Operator */BsonDocumentBuilder, _: BsonCodec<List<T>>)
fun <T> `$nin`(vararg values: T) =
    `$nin` by encode(values.asList())

/** https://www.mongodb.com/docs/manual/reference/operator/query/nin */
@BsonMarker2
context(_: /* Operator */BsonDocumentBuilder, _: BsonCodec<List<T>>)
fun <T> `$nin`(values: List<T>) =
    `$nin` by encode(values)

/** https://www.mongodb.com/docs/manual/reference/operator/query/nin */
@BsonMarker2
@JvmName($$"BsonFieldCodec<T>_$nin_List<T>")
context(_: /* Query */BsonDocumentBuilder)
infix fun <T> BsonFieldCodec<T>.`$nin`(values: List<T>) =
    this.name by { `$nin` by array { values.forEach { by(it encode this) } } }

/** https://www.mongodb.com/docs/manual/reference/operator/query/nin */
@BsonMarker2
@JvmName($$"BsonFieldCodec<List<T>>_$nin_List<T>")
context(_: /* Query */BsonDocumentBuilder)
infix fun <T> BsonFieldCodec<List<T>>.`$nin`(values: List<T>) =
    this.name by { `$nin` by (values encode this) }

/* ============= ------------------ ============= */
