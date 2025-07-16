@file:Suppress("FunctionName")

package org.cufy.ped.filters

import org.cufy.bson.*
import org.cufy.mongodb.`$all`
import org.cufy.mongodb.`$elemMatch`
import org.cufy.mongodb.`$size`
import org.cufy.ped.BsonCodec
import org.cufy.ped.BsonFieldCodec
import org.cufy.ped.encode

// https://www.mongodb.com/docs/manual/reference/operator/query/#array

/* ============= ------------------ ============= */

/** https://www.mongodb.com/docs/manual/reference/operator/query/all */
@BsonMarker2
context(_: /* Operator */BsonDocumentBuilder, _: BsonCodec<List<T>>)
fun <T> `$all`(vararg values: T) =
    `$all` by encode(values.asList())

/** https://www.mongodb.com/docs/manual/reference/operator/query/all */
@BsonMarker2
context(_: /* Operator */BsonDocumentBuilder, _: BsonCodec<List<T>>)
fun <T> `$all`(values: List<T>) =
    `$all` by encode(values)

/** https://www.mongodb.com/docs/manual/reference/operator/query/all */
@BsonMarker2
context(_: /* Query */BsonDocumentBuilder)
infix fun <T> BsonFieldCodec<List<T>>.`$all`(values: List<T>) =
    this.name by { `$all` by (values encode this) }

/* ============= ------------------ ============= */

/** https://www.mongodb.com/docs/manual/reference/operator/query/elemMatch */
@BsonMarker2
context(_: /* Query */BsonDocumentBuilder)
infix fun BsonFieldCodec</* Array */*>.`$elemMatch`(query: /* Query & Operator */BsonDocumentBlock) =
    this.name by { `$elemMatch` by query }

/** https://www.mongodb.com/docs/manual/reference/operator/query/elemMatch */
@BsonMarker2
context(_: /* Query */BsonDocumentBuilder)
infix fun BsonFieldCodec</* Array */*>.`$elemMatch`(query: /* Query & Operator */BsonDocument) =
    this.name by { `$elemMatch` by query }

/* ============= ------------------ ============= */

/** https://www.mongodb.com/docs/manual/reference/operator/query/size */
@BsonMarker2
context(_: /* Query */BsonDocumentBuilder)
infix fun BsonFieldCodec</* Array */*>.`$size`(value: Int) =
    this.name by { `$size` by value }

/** https://www.mongodb.com/docs/manual/reference/operator/query/size */
@BsonMarker2
context(_: /* Query */BsonDocumentBuilder)
infix fun BsonFieldCodec</* Array */*>.`$size`(value: Long) =
    this.name by { `$size` by value }

/* ============= ------------------ ============= */
